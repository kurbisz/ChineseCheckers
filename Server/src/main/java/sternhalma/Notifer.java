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
    /**
     * Send message to all players in game.
     * @param msg message to be send
     * @param game specific game
     */
    public void notifyAll(String msg, Game game) {
        for (Player p: game.getAllPlayers()) {
            p.notify(msg);
        }
    }
    /**
     * Send message to all players in game except for one player.
     * @param msg message to be send
     * @param game specific game
     * @param player player not to send message to
     */
    public void notifyAllExceptPlayer(String msg, Game game, Player player) {
        for (Player p: game.getAllPlayers()) {
            if (p != player) {
                p.notify(msg);
            }
        }
    }

}
