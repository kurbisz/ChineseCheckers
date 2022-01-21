package sternhalma.database;

import java.sql.Timestamp;
import java.util.Set;


/**
 * Game representation in database.
 */
public class GameEntry {
    //PRIMARY KEY
    private int gameID;
    private int boardSize;
    private int numPlayers;
    private String config;
    private Set<MoveEntry> moves;
    private Timestamp time;
    private String playersString;

    public String getPlayersString() {
        return playersString;
    }

    public void setPlayersString(String playersString) {
        this.playersString = playersString;
    }

    /**
     * Create game entry
     * @param size size of game
     * @param num number of players
     * @param con configuration string
     */
    public GameEntry(int size, int num, String con) {
        this.boardSize = size;
        this.numPlayers = num;
        this.config = con;
    }

    public GameEntry() {

    }

    /**
     * Set moves for a game
     * @param moves set of move entries
     */
    public void setMoves(Set<MoveEntry> moves) {
        this.moves = moves;
    }

    /**
     * Get configuration string
     * @return configuration string
     */
    public String getConfig() {
        return this.config;
    }

    /**
     * Get board size
     * @return board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Get number of players in game
     * @return number of players in game
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Set<MoveEntry> getMoves() {
        return moves;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFormattedTime() {
        return time.toString().substring(0,19);
    }
}
