package sternhalma.board;

import sternhalma.Game;
import sternhalma.Watch;

/**
 * Factory responsible for creating
 */
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
    public StartingInterface getWatch(BoardInterface board, int size, Watch watch) {
        return new ClassicStartWatch(board, size, watch);
    }

    @Override
    public FinishInterface getFinish(
            BoardInterface board, int size, Game game) {
        return new ClassicFinish(board, size);
    }
}
