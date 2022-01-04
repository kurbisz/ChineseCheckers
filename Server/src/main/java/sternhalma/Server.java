package sternhalma;

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

    /**
     * Create and start game server.
     * @param args args[0] -> game size
     * @throws Exception
     */
    public static void main(String[] args) {
        int size = 4;
        System.out.println("CONFIG: " + Arrays.toString(args));
        if (args.length > 1) {
            try {
                size = Integer.parseInt(args[0]);
                PORT = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("The Sternhalma server is running");
            System.out.println("IP: " + Inet4Address.getLocalHost().getHostAddress());
            System.out.println("PORT: " + PORT);
            ExecutorService pool = Executors.newFixedThreadPool(200);
            while (true) {
                Game game = new Game(size);
                Socket socket = null;
                int i=0;
                while (true) {
                    socket = listener.accept();
                    if (socket==null) {
                        return;
                    }
                    if(!game.canJoin()) {
                        game = new Game(size);
                        i = 0;
                    }
                    pool.execute(game.createPlayer(socket,i));
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
