package sternhalma.database;

import javax.persistence.*;

@Entity
@Table(name="moves")
public class MoveEntry {
    private int fromR, fromC, toR, toC;
    private int player;

    //PRIMARY KEY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moveId;
    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameEntry game; //FOREIGN KEY
    private int numSeq;

    public MoveEntry(int fR, int fC, int tC, int tR, int player, GameEntry game, int seq) {
        fromR = fR;
        fromC = fC;
        toR = tR;
        toC = tC;
        this.player = player;
        this.game = game;
        this.numSeq = seq;
    }
    public MoveEntry() {}
}
