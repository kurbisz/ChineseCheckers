import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws Exception {
        try (ServerSocket listener = new ServerSocket(59898)) {
            System.out.println("The Sternhalma server is running...");
            ExecutorService pool = Executors.newFixedThreadPool(6);
            while (true) {
                Game game = new Game();
                for(int i=0;i<6;i++)
                    pool.execute(game.createPlayer(listener.accept(),i));
            }
        }
    }
}
