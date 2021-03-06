package client;

import client.game.GameManager;
import client.graphics.GraphicsManager;
import connection.ConnectionManager;
import connection.Messenger;
import connection.NoConnectionException;

import java.io.IOException;

public class CheckersClient {

    private static CheckersClient client;
    private static boolean humanMode = true;

    private ConnectionManager connectionManager;
    private GraphicsManager graphicsManager;
    private GameManager gameManager;
    private static Messenger messenger;
    private ClientType clientType = ClientType.PLAYER;

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

    public synchronized static CheckersClient generateClient() {
        if (client == null) {
            client = new CheckersClient();
            client.openGame();
        }
        return client;
    }

    /**
     * Create new client and initialize it.
     * @param args
     */
    public static void main(String[] args) {
        setHumanMode(true);
        generateClient();
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
    public void onWindowClose() throws NoConnectionException {
        gameManager.onWindowClose();
    }

    /**
     * Connect client to the server with
     * given address and port.
     * @param serverAddress server's address
     * @param serverPort server's port
     * @param nickName nickname of a player
     * @param clientType type of client: viewer or player
     * @throws IOException when occurred problem
     * when connecting to certain server
     * @throws NoConnectionException when occurred problem
     * when connecting to certain server
     */
    public void connectClientToServer(String serverAddress,
                                      int serverPort,
                                      String nickName,
                                      ClientType clientType)
            throws IOException, NoConnectionException {
        graphicsManager.lockAppSize();
        connectionManager.createNewConnection(
                serverAddress, serverPort, nickName, clientType);
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

    /**
     * Set new messenger for other classes.
     * Should be used only for testing.
     * @param newMessenger new Messenger instance, null means that
     *                     it will return method Messenger.getInstance()
     */
    public static void setMessenger(Messenger newMessenger) {
        messenger = newMessenger;
    }

    /**
     * Gives Messenger.getInstance() when messenger was not set.
     * Otherwise returns instance from variable messenger.
     * @return actual instance of Messenger class
     */
    public static Messenger getMessenger() {
        if (messenger == null) {
            return Messenger.getInstance();
        }
        return messenger;
    }

    /**
     * Turn on/off notifications from JOptionPane and
     * other features only for human (false means tests
     * or other purposes).
     * @param mode new status of human mode
     */
    public static void setHumanMode(boolean mode) {
        humanMode = mode;
    }

    /**
     * Check if human mode is enabled or disabled.
     * @return true if human mode is enabled, false otherwise
     */
    public static boolean isHumanMode() {
        return humanMode;
    }

    /**
     * Show menu with choosing game to show (to viewer).
     * @param index array of identities of games to show
     * @param games array of names of games to show
     */
    public void chooseGame(int[] index, String[] games) {
        graphicsManager.chooseGame(index, games);
    }

    /**
     * Close client with message in option pane.
     * It doesn't save the game.
     * @param msg message which have to be shown in option pane
     */
    public void finish(String msg) {
        graphicsManager.finish(msg);
    }

    /**
     * Getter of variable clientType
     * @return actual type of client: viewer or player (enum)
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * Setter of variable clientType
     * @param clientType new type of this client: viewer or player (enum)
     */
    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
