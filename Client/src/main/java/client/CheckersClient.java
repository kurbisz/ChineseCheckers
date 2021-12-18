package client;

import client.game.GameInterpreter;
import client.game.GameManager;
import client.game.states.GameState;
import client.graphics.GraphicsManager;
import connection.ConnectionManager;
import connection.Interpreter;
import connection.Messenger;
import connection.Receiver;

import java.io.IOException;
import java.net.UnknownHostException;

public class CheckersClient {

    private static CheckersClient client;

    private ConnectionManager connectionManager;
    private GraphicsManager graphicsManager;
    private GameManager gameManager;

    public CheckersClient() {
        connectionManager = new ConnectionManager();
        graphicsManager = new GraphicsManager();
        graphicsManager.initVariables();
        gameManager = new GameManager(graphicsManager);

    }

    public void openGame() {
        graphicsManager.createNewWindow();
    }

    public void onWindowClose() {
        gameManager.onWindowClose();
    }

    public void connectClientToServer(String serverAddress, int serverPort, String nickName)
            throws IOException {
        graphicsManager.lockAppSize();
        connectionManager.createNewConnection(serverAddress, serverPort, nickName);
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