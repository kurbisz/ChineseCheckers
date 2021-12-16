package client;

import client.game.GameInterpreter;
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
    private Interpreter interpreter;

    public CheckersClient() {
        connectionManager = new ConnectionManager();
        graphicsManager = new GraphicsManager();
        graphicsManager.initVariables();
        interpreter = new GameInterpreter(GameState.WAITING_FOR_GAME, graphicsManager);
        Receiver.setInterpreter(interpreter);

    }

    public void openGame() {
        graphicsManager.createNewWindow();
    }

    public void connectClientToServer(String serverAddress, int serverPort, String nickName)
            throws UnknownHostException, IOException {
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