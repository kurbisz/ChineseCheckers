package sternhalma.board;

import sternhalma.Game;

public abstract class RulesFactory {
    public abstract BoardInterface getBoard(int n, MovingInterface mv);
    public abstract MovingInterface getMoving();
    public abstract StartingInterface getStart(BoardInterface board, int size, Game game);
    public abstract FinishInterface getFinish(BoardInterface board, int size, Game game);
}
