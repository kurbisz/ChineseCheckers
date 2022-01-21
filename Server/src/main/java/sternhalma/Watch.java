package sternhalma;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sternhalma.board.*;
import sternhalma.database.*;
import sternhalma.exceptions.CannotPlayMove;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * Watching a game.
 */
public class Watch implements Runnable {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private Database database;
    private List<MoveEntry> moves;
    private BoardInterface board;
    private StartingInterface starting;
    private int seq = 0;

    /**
     * Construct watching environment for specific socket.
     * @param socket user socket
     */
    public Watch(Socket socket) {
        this.socket = socket;
    }

    /**
     * Invoke next move.
     */
    public void next() throws CannotPlayMove {
        if (seq == moves.size()) {
            throw new CannotPlayMove();
        }
        MoveEntry entry = moves.get(seq);
        seq++;
        int fromR = entry.getFromR();
        int fromC = entry.getFromC();
        int toR = entry.getToR();
        int toC = entry.getToC();
        send(String.format("MOVE %d %d %d %d", fromR, fromC, toR, toC));
    }

    /**
     * Invoke previous move.
     */
    public void previous() throws CannotPlayMove {
        if (seq == 0) {
            throw new CannotPlayMove();
        }
        MoveEntry entry = moves.get(seq);
        seq++;
        int toR = entry.getFromR();
        int toC = entry.getFromC();
        int fromR = entry.getToR();
        int fromC = entry.getToC();
        send(String.format("MOVE %d %d %d %d", fromR, fromC, toR, toC));
    }

    /**
     * Send message to client.
     * @param message message to be sent
     */
    public void send(String message) {
        output.println(message);
    }
    private void select(int id) {
        GameEntry gameEntry = database.getGame(id);
        send("SIZE " + gameEntry.getBoardSize() + " " + gameEntry.getNumPlayers());
        send("PLAYERS#"+gameEntry.getPlayersString());
        moves = new ArrayList<>(database.getMoves(id));
        FactoryProducer factory = FactoryProducer.getInstance();
        RulesFactory rules = factory.getFactory(gameEntry.getConfig());
        this.board = rules.getBoard(gameEntry.getBoardSize(), new BasicMove());
        this.starting = rules.getWatch(board, gameEntry.getBoardSize(), this);
        starting.prepare(gameEntry.getNumPlayers());
    }
    private void setup() throws IOException {
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        database = (MySQLDatabase) context.getBean("db");

    }
    private void processCommands() {
        while (input.hasNextLine()) {
            try {
                String command = input.nextLine();
                if (command.startsWith("NEXT")) {
                    next();
                } else if (command.startsWith("PREVIOUS")) {
                    previous();
                } else if (command.startsWith("SELECT")) {
                    String[] data = command.split(" ");
                    int id = Integer.parseInt(data[1]);
                    select(id);
                }
            } catch (CannotPlayMove cannotPlayMove) {
                send("MESSAGE CANNOT PLAY MOVE");
            }

        }
    }
    @Override
    public void run() {
        try {
            setup();
            sendList();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                //error in connection
            }
        }
    }

    private void sendList() {
        StringBuilder msg = new StringBuilder();
        for (GameEntry entry : database.getGames()) {
            msg.append(entry.getGameID());
            msg.append(";");
            msg.append(entry.getFormattedTime());
            msg.append("$");
        }
        send("LIST#" + msg);
    }
}
