package sternhalma.board;

import org.junit.jupiter.api.Test;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @Test
    void moveSimple() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        try {
            b.move(5,0,0,1,1);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(1,1).getOwner(),5);
    }

    @Test
    void moveMore() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        try {
            b.move(5,0,0,2,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(2,0).getOwner(),5);
    }


    @Test
    void moveTwo() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        try {
            b.move(5,0,0,1,1);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(1,1).getOwner(),5);
        InvalidMoveException e = assertThrows(InvalidMoveException.class,()->b.move(5,1,1,1,0));
    }
    @Test
    void moveUnconnected() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        InvalidMoveException e = assertThrows(InvalidMoveException.class,()->b.move(5,0,0,5,0));
    }
    @Test
    void movePlayer() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        InvalidPlayerException e = assertThrows(InvalidPlayerException.class,()->b.move(2,0,0,1,0));
    }
    @Test
    void moveTaken() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        InvalidMoveException e = assertThrows(InvalidMoveException.class,()->b.move(5,0,0,1,0));
    }
    @Test
    void moveJumpS() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        b.getField(3,0).setOwner(5);
        try {
            b.move(5,0,0,2,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        try {
            b.move(5,2,0,4,4);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(2,0).getOwner(),-1);
        assertEquals(b.getField(4,4).getOwner(),5);
    }
    @Test
    void moveJumpF() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        try {
            b.move(5,0,0,2,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(2,0).getOwner(),5);
        InvalidMoveException e = assertThrows(InvalidMoveException.class,()->b.move(5,2,0,3,0));

    }
    @Test
    void moveJumpF2() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        try {
            b.move(5,0,0,2,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(2,0).getOwner(),5);
        InvalidMoveException e = assertThrows(InvalidMoveException.class,()->b.move(5,1,0,1,1));

    }
    @Test
    void moveJumpS2() {
        Board b = new Board(4);
        b.getField(0,0).setOwner(5);
        b.getField(1,0).setOwner(5);
        try {
            b.move(5,0,0,2,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        b.endMove();
        try {
            b.move(5,2,0,3,0);
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
        assertEquals(b.getField(0,0).getOwner(),-1);
        assertEquals(b.getField(2,0).getOwner(),-1);
        assertEquals(b.getField(3,0).getOwner(),5);
    }
}