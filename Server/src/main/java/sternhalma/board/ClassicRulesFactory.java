package sternhalma.board;

import sternhalma.Game;

public class ClassicRulesFactory extends RulesFactory {
    @Override
    public BoardInterface getBoard(int n, MovingInterface mv) {
        return new ClassicBoard(n, mv);
    }
    @Override
    public MovingInterface getMoving() {
        return new ClassicMove();
    }
    @Override
    public StartingInterface getStart(
            BoardInterface board, int size, Game game) {
        return new ClassicStart(board, size, game);
    }
    @Override
    public FinishInterface getFinish(
            BoardInterface board, int size, Game game) {
        return new ClassicFinish(board, size);
    }
}
