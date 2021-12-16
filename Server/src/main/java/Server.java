import java.net.Inet4Address;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static final int PORT = 59898;
    public static void main(String[] args) throws Exception {
        int size=4;
        System.out.println(Arrays.toString(args));
        if(args.length>0) {
            try {
                size=Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        try (ServerSocket listener = new ServerSocket(PORT)) {
            System.out.println("The Sternhalma server is running");
            System.out.println("IP: "+Inet4Address.getLocalHost().getHostAddress());
            System.out.println("PORT: "+PORT);
            ExecutorService pool = Executors.newFixedThreadPool(6);
            while (true) {
                Game game = new Game(size);
                for(int i = 0; i < 6; i++) {
                    pool.execute(game.createPlayer(listener.accept(), i));
                }
            }
        }
    }
}
