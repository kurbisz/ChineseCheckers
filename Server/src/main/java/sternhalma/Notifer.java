package sternhalma;

public class Notifer implements NotiferInterface {
    private static Notifer instance = null;
    public static Notifer getInstance() {
        if (instance == null) {
            instance = new Notifer();
        }
        return instance;
    }
    public void notifyAll(String msg, Game game) {
        for (Player p: game.getAllPlayers()) {
            p.notify(msg);
        }
    }
    public void notifyAllExceptPlayer(String msg, Game game, Player player) {
        for (Player p: game.getAllPlayers()) {
            if (p != player) {
                p.notify(msg);
            }
        }
    }

}