import client.GraphicsManager;
import connection.ConnectionManager;

public class CheckersClient {

    private static CheckersClient client;

    private ConnectionManager connectionManager;
    private GraphicsManager graphicsManager;

    public CheckersClient() {
        connectionManager = new ConnectionManager();
        graphicsManager = new GraphicsManager();

        graphicsManager.createNewWindow();

    }

    private void createNewWindow() {
        graphicsManager = new GraphicsManager();
    }


    public static CheckersClient getInstance() {
        return client;
    }

    public static void main(String[] args) {
        client = new CheckersClient();
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

}
