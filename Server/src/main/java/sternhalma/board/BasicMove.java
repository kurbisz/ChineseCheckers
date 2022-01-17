package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

/**
 * Basic move implementation without verification.
 */
public class BasicMove implements MovingInterface {
    @Override
    public void move(int id, Field from, Field to) throws InvalidMoveException, InvalidPlayerException {
        from.setOwner(-1);
        to.setOwner(id);
    }

    @Override
    public void end() {

    }

    @Override
    public void setFinish(FinishInterface finish) {

    }
}
