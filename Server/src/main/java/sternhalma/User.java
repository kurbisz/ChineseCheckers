package sternhalma;

import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * User of game server.
 */
public class User implements Runnable {
    private Socket socket;
    private Scanner input;
    private PrintWriter output;
    private Server server;
    public User(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * Get user socket.
     * @return socket
     */
    public Socket getSocket() {
        return this.socket;
    }
    private void setup() throws IOException {
        // Poczatek gry
        input = new Scanner(socket.getInputStream());
        output = new PrintWriter(socket.getOutputStream(), true);
    }
    private int processCommands() {
        while (input.hasNextLine()) {
            String command = input.nextLine();
            if (command.startsWith("WATCH")) {
                server.watch(this);
                return 0;
            } else if (command.startsWith("JOIN")) {
                server.join(this);
                return 0;
            }
        }
        return -1;
    }
    @Override
    public void run() {
        int i = -1;
        try {
            setup();
            i = processCommands();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (i == 0) {
                return;
            }
            try {
                socket.close();
            } catch (IOException e) {
                //error in connection
            }
        }
    }
}
