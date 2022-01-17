package sternhalma.database;

import java.util.Set;

/**
 * Reader from database.
 */
public class MySQLReader implements Reader {
    private static MySQLReader instance = null;
    /**
     * Get the instance
     * @return instance of MySQLReader
     */
    public static MySQLReader getInstance() {
        if (instance == null) {
            instance = new MySQLReader();
        }
        return instance;
    }

    /**
     * Get list of games in database.
     * @return formatted string of games and times
     */
    @Override
    public String getGames() {
        return null;
    }

    /**
     * Get set of moves from game of given id
     * @param id id of game
     * @return set of moves
     */
    @Override
    public Set<MoveEntry> getMoves(int id) {
        return null;
    }

    /**
     * Get game information about game with given id
     * @param id id of game
     * @return game info
     */
    @Override
    public GameEntry getGame(int id) {
        return null;
    }
}
