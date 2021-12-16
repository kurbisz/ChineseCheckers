import exceptions.CannotStartGameException;
import exceptions.InvalidMoveException;
import exceptions.InvalidPlayerException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable {
    private int number;
    private String name;
    private Socket socket;
    private Player next;
    private Scanner input;
    private PrintWriter output;
    private Game game;
    private Notifer notifer = Notifer.getInstance();
    private int position = 0;
    public void notify(String msg) {
        output.println(msg);
    }
    public Player(Socket accept, int i, Game game) {
        this.number = i;
        this.socket = accept;
        this.game = game;
    }
    public void setNext(Player next) {
        this.next = next;
    }
    public Player getNext() {
        return this.next;
    }
    public String getName() {
        return this.name;
    }
    private void setup() throws IOException {
        // Poczatek gry
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        notify("MESSAGE WELCOME " + number);
        notify("SIZE "+ game.getSize()+" "+game.numPlayers());
    }
    public void setPosition(int i) {
        this.position = i;
    }
    public void notifyStart() {
        notify("START "+position);
    }
    private void processCommands() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            try {
                if (command.startsWith("START")) {
                    game.start();
                } else if (command.startsWith("QUIT")) {
                    return;
                } else if (command.startsWith("MOVE")) {
                    String[] data = command.split(" ");
                    int from = Integer.parseInt(data[1]);
                    int to = Integer.parseInt(data[2]);
                    game.move(this, from, to);
                } else if (command.startsWith("NAME")) {
                    this.name = command.substring(4);
                } else if (command.startsWith("PASS")) {
                    //TODO
                }
            } catch (CannotStartGameException e) {
                notify("MESSAGE Game cannot be started");
            } catch (InvalidMoveException | NumberFormatException e) {
                notify("MESSAGE Invalid move");
            } catch (InvalidPlayerException e) {
                notify("MESSAGE It's not your turn");
            }
        }
    }


    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            notifer.notifyAll("MESSAGE PLAYER " + number + " LEFT", game);
            notifer.notifyAll("LEFT", game);
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
