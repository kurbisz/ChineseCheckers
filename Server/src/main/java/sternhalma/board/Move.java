package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;

public class Move implements MovingInterface{
    private Field current;
    private boolean last=false;
    @Override
    public void move(int id, Field from, Field to) throws InvalidMoveException {
        if (last) {
            throw new InvalidMoveException();
        }
        if (current!=null&&current!=from) {
            throw new InvalidMoveException();
        }
        if(from.getNeighbours().contains(to)) {
            last = true;
            from.setOwner(-1);
            to.setOwner(id);
        }
    }

    @Override
    public void end() {
        current = null;
        last = false;
    }
}
