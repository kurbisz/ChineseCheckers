package sternhalma.database;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

/**
 * Game representation in database.
 */
@Entity
@Table(name = "games")
public class GameEntry {
    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gameID;
    private int boardSize;
    private int numPlayers;
    private String config;
    @OneToMany(cascade = ALL, mappedBy = "game")
    private Set<MoveEntry> moves;

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
    //DATE?
}
