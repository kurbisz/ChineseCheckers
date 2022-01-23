package sternhalma.database;

import java.sql.Timestamp;
import java.util.List;


/**
 * Game representation in database.
 */
public class GameEntry {
    //PRIMARY KEY
    private int gameID;
    private int boardSize;
    private int numPlayers;
    private String config;
    private List<MoveEntry> moves;
    private Timestamp time;
    private String playersString;

    public String getPlayersString() {
        return playersString;
    }

    public void setPlayersString(String playersString) {
        this.playersString = playersString;
    }

    /**
     * Create game entry.
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
     * Set moves for a game.
     * @param moves set of move entries
     */
    public void setMoves(List<MoveEntry> moves) {
        this.moves = moves;
    }

    /**
     * Get configuration string.
     * @return configuration string
     */
    public String getConfig() {
        return this.config;
    }

    /**
     * Get board size.
     * @return board size
     */
    public int getBoardSize() {
        return this.boardSize;
    }

    /**
     * Get number of players in game.
     * @return number of players in game
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * Get game id.
     * @return game id
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Set game id.
     * @param gameID gameID to set
     */
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Set boardsize.
     * @param boardSize boardsize
     */
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * Set number of players.
     * @param numPlayers number of players
     */
    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Set configuration string.
     * @param config configuration string
     */
    public void setConfig(String config) {
        this.config = config;
    }

    /**
     * Get list of saved moves.
     * @return list of saved moves
     */
    public List<MoveEntry> getMoves() {
        return moves;
    }

    /**
     * Get game saved time.
     * @return game saved time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * Set game saved time.
     * @param time game saved time
     */
    public void setTime(Timestamp time) {
        this.time = time;
    }

    /**
     * Get timestamp in formatted format.
     * @return timestamp as string yyyy-mm-dd hh:mm:ss
     */
    public String getFormattedTime() {
        return time.toString().substring(0,19);
    }
}
