package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;
/**
 * Interface representing the board.
 */
public interface BoardInterface {
    /**
     * Get field at specific location.
     * @param row row
     * @param col column
     * @return field
     */
    Field getField(int row, int col);
    /**
     * Get size of specific row.
     * @param y row number
     * @return row size
     */
    int getRowSize(int y);
    /**
     * Move a piece from one field to another.
     * @param id id of player performing move
     * @param fromR row from which to move
     * @param fromC column from which to move
     * @param toR row to which to move
     * @param toC column to which to move
     * @throws InvalidMoveException move cannot be proceeded
     * @throws InvalidPlayerException move cannot be proceeded
     */
    void move(int id, int fromR, int fromC, int toR, int toC)
            throws InvalidMoveException, InvalidPlayerException;
    /**
     * End move.
     */
    void endMove();
}
