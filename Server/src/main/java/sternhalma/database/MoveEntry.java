package sternhalma.database;

/**
 * Move representation in database.
 */

public class MoveEntry {
    private int fromR, fromC, toR, toC;
    private int player;

    //PRIMARY KEY
    private int moveId;
    private GameEntry game; //FOREIGN KEY
    private int numSeq;

    public MoveEntry(int fR, int fC, int tR, int tC, int player, GameEntry game, int seq) {
        fromR = fR;
        fromC = fC;
        toR = tR;
        toC = tC;
        this.player = player;
        this.game = game;
        this.numSeq = seq;
    }
    public MoveEntry() {

    }

    public int getFromR() {
        return this.fromR;
    }

    public int getFromC() {
        return this.fromC;
    }

    public int getToR() {
        return this.toR;
    }

    public int getToC() {
        return this.toC;
    }

    public void setFromR(int fromR) {
        this.fromR = fromR;
    }

    public void setFromC(int fromC) {
        this.fromC = fromC;
    }

    public void setToR(int toR) {
        this.toR = toR;
    }

    public void setToC(int toC) {
        this.toC = toC;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getMoveId() {
        return moveId;
    }

    public void setMoveId(int moveId) {
        this.moveId = moveId;
    }

    public GameEntry getGame() {
        return game;
    }

    public void setGame(GameEntry game) {
        this.game = game;
    }

    public int getNumSeq() {
        return numSeq;
    }

    public void setNumSeq(int numSeq) {
        this.numSeq = numSeq;
    }
}
