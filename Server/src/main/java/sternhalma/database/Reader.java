package sternhalma.database;

import java.util.Set;

public interface Reader {
    /**
     * Get list of games in database.
     * @return formatted string of games and times
     */
    String getGames();
    /**
     * Get set of moves from game of given id
     * @param id id of game
     * @return set of moves
     */
    Set<MoveEntry> getMoves(int id);
    /**
     * Get game information about game with given id
     * @param id id of game
     * @return game info
     */
    GameEntry getGame(int id);
}
