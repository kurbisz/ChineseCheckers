package connection;

public class Messenger {
    private static Messenger instance;
    private static ConnectionManager cmd;
    public static Messenger getInstance() {
        if (instance == null) {
            instance = new Messenger();
        }
        return instance;
    }
    public static void setCMD(ConnectionManager cmd) {
        Messenger.cmd = cmd;
    }
    public void pass() {
        cmd.send("PASS");
    }
    public void move(int num) {
        cmd.send("MOVE " + num);
    }
    public void end() {
        cmd.send("QUIT");
    }
    public void name(String name) {
        cmd.send("NAME " + name);
    }
    public void start() {
        cmd.send("START");
    }
}