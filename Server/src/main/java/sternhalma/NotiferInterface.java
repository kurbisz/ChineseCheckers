package sternhalma;

/**
 * Interface to notifying players.
 */
public interface NotiferInterface {
    /**
     * Send message to all players in game.
     * @param msg message to be send
     * @param game specific game
     */
    void notifyAll(String msg, Game game);
    /**
     * Send message to all players in game except for one player.
     * @param msg message to be send
     * @param game specific game
     * @param player player not to send message to
     */
    void notifyAllExceptPlayer(String msg, Game game, Player player);
}
