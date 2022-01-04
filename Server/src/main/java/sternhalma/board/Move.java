package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

/**
 * Class implementing sternhalma classic move rules.
 */
public class Move implements MovingInterface{
    private boolean last = false;
    private boolean moved = false;
    private Field current = null;
    @Override
    public void move(int id, Field from, Field to) throws InvalidMoveException, InvalidPlayerException {

        if (from.getOwner()!=id) {
            throw new InvalidPlayerException();
        }
        if (to.getOwner()!=-1) {
            throw new InvalidMoveException();
        }
        if (last) {
            throw new InvalidMoveException();
        }
        if (current != null && current!=from) {
            throw new InvalidMoveException();
        }
        if (!moved && from.getNeighbours().contains(to)) {
            last = true;
            from.setOwner(-1);
            to.setOwner(id);
            return;
        }
        for (Field f : from.getNeighbours()) {
            if (f.getOwner() != -1 && f.getNeighbours().contains(to)) {
                moved = true;
                last = false;
                from.setOwner(-1);
                to.setOwner(id);
                current = to;
                return;
            }
        }
        throw new InvalidMoveException();
    }

    @Override
    public void end() {
        moved = false;
        current = null;
        last = false;
    }
}
