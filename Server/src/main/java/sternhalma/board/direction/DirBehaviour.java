package sternhalma.board.direction;

/**
 * Interface describing directions on the board.
 */
public interface DirBehaviour {
    /**
     * Set opposite direction.
     * @param n opposite direction
     */
    void setOpposite(DirBehaviour n);

    /**
     * Get opposite direction.
     * @return opposite direction
     */
    DirBehaviour getOpposite();
}
