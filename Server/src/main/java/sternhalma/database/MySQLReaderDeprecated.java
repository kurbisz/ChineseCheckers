package sternhalma.database;

import java.util.Set;

/**
 * Reader from database.
 */
@Deprecated
public class MySQLReaderDeprecated {
    private static MySQLReaderDeprecated instance = null;
    /**
     * Get the instance
     * @return instance of MySQLReader
     */
    public static MySQLReaderDeprecated getInstance() {
        if (instance == null) {
            instance = new MySQLReaderDeprecated();
        }
        return instance;
    }

    /**
     * Get list of games in database.
     * @return formatted string of games and times
     */
    public String getGames() {
        return null;
    }

    /**
     * Get set of moves from game of given id
     * @param id id of game
     * @return set of moves
     */
    public Set<MoveEntry> getMoves(int id) {
        return null;
    }

    /**
     * Get game information about game with given id
     * @param id id of game
     * @return game info
     */
    public GameEntry getGame(int id) {
        return null;
    }
}
