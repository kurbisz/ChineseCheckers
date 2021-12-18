package client;

import client.game.GameManager;
import client.graphics.GraphicsManager;
import connection.ConnectionManager;

import java.io.IOException;

public class CheckersClient {

    private static CheckersClient client;

    private ConnectionManager connectionManager;
    private GraphicsManager graphicsManager;
    private GameManager gameManager;

    /**
     * Main client class with all crucial
     * class instances. Creates new connection,
     * graphics and game manager.
     */
    public CheckersClient() {
        connectionManager = new ConnectionManager();
        graphicsManager = new GraphicsManager();
        graphicsManager.initVariables();
        gameManager = new GameManager(graphicsManager);
    }

    /**
     * Get instance of this class in a static way
     * (it is singleton).
     * @return instance of this class
     */
    public static CheckersClient getInstance() {
        return client;
    }

    /**
     * Create new client and initialize it.
     * @param args
     */
    public static void main(String[] args) {
        client = new CheckersClient();
        client.openGame();
    }

    /**
     * Create new application with game.
     */
    public void openGame() {
        graphicsManager.createNewWindow();
    }

    /**
     * Execute onWindowClose() from GameManager
     * on application close.
     */
    public void onWindowClose() {
        gameManager.onWindowClose();
    }

    /**
     * Connect client to the server with
     * given address and port.
     * @param serverAddress server's address
     * @param serverPort server's port
     * @param nickName nickname of a player
     * @throws IOException when occurred problem when connecting to certain server
     */
    public void connectClientToServer(String serverAddress, int serverPort, String nickName)
            throws IOException {
        graphicsManager.lockAppSize();
        connectionManager.createNewConnection(serverAddress, serverPort, nickName);
    }

    /**
     * Get actual graphics manager.
     * @return stored graphics manager
     */
    public GraphicsManager getGraphicsManager() {
        return graphicsManager;
    }

    /**
     * Get actual connection manager.
     * @return stored connection manager
     */
    public ConnectionManager getConnectionManager() {
        return connectionManager;
    }

    /**
     * Get actual game manager.
     * @return stored game manager
     */
    public GameManager getGameManager() {
        return gameManager;
    }
}