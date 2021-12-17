package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;

public interface MovingInterface {

    void move(int id, Field from, Field to) throws InvalidMoveException;
    void end();
}
