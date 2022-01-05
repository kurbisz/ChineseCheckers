package sternhalma.board.direction;

/**
 * Interface describing directions on the board.
 */
public interface DirBehaviour {
    void setOpposite(DirBehaviour n);
    DirBehaviour getOpposite();
}
