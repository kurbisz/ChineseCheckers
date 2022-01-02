package sternhalma;

/**
 * Singleton responsible for notifying players.
 */
public class Notifer implements NotiferInterface {
    private static Notifer instance = null;

    /**
     * Get the notifer
     * @return instance of notifer
     */
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
