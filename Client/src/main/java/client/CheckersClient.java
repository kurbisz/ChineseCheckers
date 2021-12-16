package client;

import client.graphics.GraphicsManager;
import connection.ConnectionManager;

import java.io.IOException;
import java.net.UnknownHostException;

public class CheckersClient {

    private static CheckersClient client;

    private ConnectionManager connectionManager;
    private GraphicsManager graphicsManager;

    public CheckersClient() {
        connectionManager = new ConnectionManager();
        graphicsManager = new GraphicsManager();
    }

    public void openGame() {
        graphicsManager.createNewWindow();
    }

    public void connectClientToServer(String serverAddress, int serverPort) throws UnknownHostException, IOException {
        connectionManager.createNewConnection(serverAddress, serverPort);
        graphicsManager.drawBoard();
    }

    public static CheckersClient getInstance() {
        return client;
    }

    public static void main(String[] args) {
        client = new CheckersClient();
        client.openGame();
    }

    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

}