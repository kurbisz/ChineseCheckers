package sternhalma;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Game server.
 */
public class Server {
    private static int PORT = 59898;
    private static final Logger logger = LogManager.getLogger(Server.class);

    private static int size = 4;
    private Game game;
    private int number = 0;
    private ExecutorService pool = Executors.newFixedThreadPool(200);

    /**
     * User joins a game.
     * @param u User
     */
    public void join(User u) {
        if (!game.canJoin()) {
            game = new Game(size);
            number = 0;
        }
        Socket socket = u.getSocket();
        pool.execute(game.createPlayer(socket, number));
        number++;
    }

    /**
     * User watches a game.
     * @param u User
     */
    public void watch(User u) {
        Socket socket = u.getSocket();
        pool.execute(new Watch(socket));
    }
    /**
     * Listen to server on set port.
     */
    public void listen() {
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("The Sternhalma server is running");
            System.out.println(
                    "IP: " + Inet4Address.getLocalHost().getHostAddress());
            System.out.println("PORT: " + PORT);
            Socket socket;
            game = new Game(size);
            while (true) {
                socket = listener.accept();
                if (socket == null) {
                    return;
                }
                pool.execute(new User(socket, this));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Create and start game server.
     * @param args args[0] -> game size
     */
    public static void main(String[] args) {
        BasicConfigurator.configure();
        System.out.println("CONFIG: " + Arrays.toString(args));
        if (args.length > 0) {
            try {
                size = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (args.length > 1) {
            try {
                PORT = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        Server server = new Server();
        server.listen();
    }
}
