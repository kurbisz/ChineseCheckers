package sternhalma.board;

import java.util.List;

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

    /**
     * Prepare interface for given number of players.
     * @param num number of players
     */
    void setPlayers(int num);

    /**
     * Get final corner of given player.
     * @param player player id
     * @return list of all fields in the corner
     */
    List<Field> getCorner(int player);
}
