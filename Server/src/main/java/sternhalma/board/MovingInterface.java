package sternhalma.board;

import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

public interface MovingInterface {

    void move(int id, Field from, Field to) throws InvalidMoveException, InvalidPlayerException;
    void end();
}
