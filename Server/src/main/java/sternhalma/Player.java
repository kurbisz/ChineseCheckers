package sternhalma;

import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class representing a player.
 */
public class Player implements Runnable {
    private int number;
    private String name;
    private Socket socket;
    private Player next;
    private Scanner input;
    private PrintWriter output;
    private Game game;
    @Deprecated
    private int position = 0;

    /**
     * Send message to the player.
     * @param msg message to be send.
     */
    public void notify(String msg) {
        output.println(msg);
    }

    /**
     * Create a player.
     * @param accept socket of player
     * @param i player id
     * @param game player's game
     */
    public Player(Socket accept, int i, Game game) {
        this.number = i;
        this.socket = accept;
        this.game = game;
    }

    /**
     * Get player's id.
     * @return player id
     */
    public int getId() {
        return this.number;
    }

    /**
     * Set next player.
     * @param next the following player in game
     */
    public void setNext(Player next) {
        this.next = next;
    }

    /**
     * Get next player
     * @return the following player in game
     */
    public Player getNext() {
        return this.next;
    }

    /**
     * Get player's name.
     * @return player's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set up the connection.
     * @throws IOException error in connection
     */
    private void setup() throws IOException {
        // Poczatek gry
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
        notify("MESSAGE WELCOME " + number);
        notify("SIZE " +  game.getSize() + " " + game.numPlayers());
        notify("SPN " + number);
    }

    /**
     * Set player's position in game.
     * @param i position in game
     */
    @Deprecated
    public void setPosition(int i) {
        this.position = i;
    }

    /**
     * Notify player about game start.
     */
    public void notifyStart() {
        notify("START");
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
                    int fromR = Integer.parseInt(data[1]);
                    int fromC = Integer.parseInt(data[2]);
                    int toR = Integer.parseInt(data[3]);
                    int toC = Integer.parseInt(data[4]);
                    game.move(this, fromR, fromC, toR, toC);
                } else if (command.startsWith("NAME")) {
                    this.name = command.substring(4);
                    game.sendNames();
                } else if (command.startsWith("PASS")) {
                    game.switchPlayer(this);
                } else if (command.startsWith("LEAVE")) {
                    game.leave(this);
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

    /**
     * Hold the connection.
     */

    @Override
    public void run() {
        try {
            setup();
            processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            game.leave(this);
            try {
                socket.close();
            } catch (IOException e) {
                //error in connection
            }
        }
    }
}
