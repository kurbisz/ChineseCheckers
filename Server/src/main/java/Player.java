import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player implements Runnable{
    private int number;
    private String name;
    private Socket socket;
    private Player next;
    private Scanner input;
    private PrintWriter output;
    private Game game;
    private Notifer notifer = Notifer.getInstance();
    public void notify(String msg)
    {
        output.println(msg);
    }
    public Player(Socket accept, int i, Game game)
    {
        this.number = i;
        this.socket = accept;
        this.game = game;
    }
    public void setNext(Player next)
    {
        this.next = next;
    }
    public Player getNext()
    {
        return this.next;
    }
    public String getName()
    {
        return this.name;
    }
    private void setup() throws IOException {
        // Poczatek gry
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        output.println("WELCOME " + number);
    }
    private void processCommands() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            if (command.startsWith("START")) {
                game.start();
            } else if (command.startsWith("QUIT")) {
                return;
            } else if (command.startsWith("MOVE")) {
                //TODO
            } else if (command.startsWith("NAME")) {
                this.name=command.substring(4);
            } else if (command.startsWith("PASS")) {
                //TODO
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
            notifer.notifyAll("PLAYER "+number+" LEFT", game);
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }
}
