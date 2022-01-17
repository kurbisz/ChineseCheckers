package sternhalma.database;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name="games")
public class GameEntry {
    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int gameID;
    private int boardSize;
    private int numPlayers;
    private String config;
    @OneToMany(cascade=ALL, mappedBy="game")
    private Set<MoveEntry> moves;
    public GameEntry(int size, int num, String con) {
        this.boardSize = size;
        this.numPlayers = num;
        this.config = con;
    }

    public GameEntry() {

    }

    public void setMoves(Set<MoveEntry> moves) {
        this.moves = moves;
    }
    //DATE?
}
