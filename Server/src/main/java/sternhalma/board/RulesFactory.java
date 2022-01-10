package sternhalma.board;

import sternhalma.Game;

/**
 * Abstract factory for creating game's rules.
 */
public abstract class RulesFactory {
    /**
     * Get board.
     * @param n size of the board
     * @param mv rules of moving on the board
     * @return created board
     */
    public abstract BoardInterface getBoard(int n, MovingInterface mv);

    /**
     * Get moving rules.
     * @return moving rules
     */
    public abstract MovingInterface getMoving();

    /**
     * Get starting interface.
     * @param board board on which it'll operate
     * @param size size of the board
     * @param game game connected to the board
     * @return starting rules
     */
    public abstract StartingInterface getStart(BoardInterface board, int size, Game game);

    /**
     * Get finishing interface.
     * @param board board on which it'll operate
     * @param size size of the board
     * @param game game connected to the board
     * @return finishing rules
     */
    public abstract FinishInterface getFinish(BoardInterface board, int size, Game game);
}
