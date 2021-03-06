package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

/**
 * Interface to perform moves.
 */
public interface MovingInterface {
    /**
     * Move a piece from one field to another.
     * @param id id of player performing move.
     * @param from starting field
     * @param to ending field
     * @throws InvalidMoveException move cannot be proceeded
     * @throws InvalidPlayerException move cannot be proceeded
     */
    void move(int id, Field from, Field to) throws InvalidMoveException, InvalidPlayerException;

    /**
     * End the turn.
     */
    void end();

    /**
     * Set the finish interface for additional rules.
     * @param finish FinishInterface to be used in other rules
     */
    void setFinish(FinishInterface finish);
}
