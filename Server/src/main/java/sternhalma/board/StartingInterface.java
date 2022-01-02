package sternhalma.board;

/**
 * Interface to generate pieces at the beginning of the game.
 */
public interface StartingInterface {
    /**
     * Generate pieces
     * @param num number of players
     */
    void prepare(int num);
}
