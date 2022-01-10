package connection;

/**
 * Singleton class responsible for sending data to server.
 */
public class Messenger {
    private static Messenger instance;
    private static ConnectionManager cmd;
    public static Messenger getInstance() {
        if (instance == null) {
            instance = new Messenger();
        }
        return instance;
    }

    /**
     * Set connection manager.
     * @param cmd connection manager
     */
    public static void setCMD(ConnectionManager cmd) {
        Messenger.cmd = cmd;
    }

    /**
     * Player ended move.
     * @throws NoConnectionException
     */
    public void pass() throws NoConnectionException {
        cmd.send("PASS");
    }

    /**
     * Player moved.
     * @param fromR number of row of source field
     * @param fromC number in row of source field
     * @param toR number of row of target field
     * @param toC number in row of target field
     * @throws NoConnectionException
     */
    public void move(int fromR, int fromC, int toR, int toC) throws NoConnectionException {
        cmd.send("MOVE "+fromR+" "+fromC+" "+toR+" "+toC);
    }

    /**
     * Player left.
     * @throws NoConnectionException
     */
    public void leave() throws NoConnectionException {
        cmd.send("LEAVE");
    }

    /**
     * Send player name to server.
     * @param name Player name
     * @throws NoConnectionException
     */
    public void name(String name) throws NoConnectionException {
        cmd.send("NAME " + name);
    }

    /**
     * Player started the game.
     * @throws NoConnectionException
     */
    public void start() throws NoConnectionException {
        cmd.send("START");
    }
}
