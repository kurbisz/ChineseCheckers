package sternhalma.database;


import java.util.List;

/**
 * Class representing all database functions.
 */
public interface Database {
    /**
     * Save game representation together with moves.
     * @param entry game representation
     */
    void addGame(GameEntry entry);
    /**
     * Get list of games in database.
     * @return formatted string of games and times.
     */
    List<GameEntry> getGames();
    /**
     * Get set of moves from game of given id.
     * @param id id of game
     * @return set of moves
     */
    List<MoveEntry> getMoves(int id);
    /**
     * Get game information about game with given id.
     * @param id id of game
     * @return game info
     */
    GameEntry getGame(int id);
}
