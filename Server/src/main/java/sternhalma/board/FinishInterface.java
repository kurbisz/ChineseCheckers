package sternhalma.board;

/**
 * Interface checking if game is finished.
 */
public interface FinishInterface {
    /**
     * Check if game is finished.
     * @param num number of players
     * @return winner id (-1 if none)
     */
    int checkEnd(int num);
}
