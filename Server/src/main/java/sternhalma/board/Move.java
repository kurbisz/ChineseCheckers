package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

public class Move implements MovingInterface{
    private Field current;
    private boolean last=false;
    @Override
    public void move(int id, Field from, Field to) throws InvalidMoveException, InvalidPlayerException {

        if(from.getOwner()!=id) {
            throw new InvalidPlayerException();
        }
        if (last) {
            throw new InvalidMoveException();
        }
        if (current!=null&&current!=from) {
            throw new InvalidMoveException();
        }
        if (from.getNeighbours().contains(to)) {
            last = true;
            from.setOwner(-1);
            to.setOwner(id);
            return;
        }
        for (Field f : from.getNeighbours()) {
            if (f.getOwner() != -1 && f.getNeighbours().contains(to)) {
                last = false;
                from.setOwner(-1);
                to.setOwner(id);
                return;
            }
        }
        throw new InvalidMoveException();
    }

    @Override
    public void end() {
        current = null;
        last = false;
    }
}
