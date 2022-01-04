package sternhalma.board;

import org.junit.jupiter.api.Test;
import sternhalma.Game;

import static org.junit.jupiter.api.Assertions.*;

class FinishTest {
    @Test
    void End2(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(0,0).setOwner(1);
        assertEquals(s.checkEnd(2),1);
    }
    @Test
    void End2v2(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(4,0).setOwner(0);
        assertEquals(s.checkEnd(2),0);
    }
    @Test
    void End6(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(1,0).setOwner(2);
        assertEquals(s.checkEnd(6),2);
    }
    @Test
    void End6v2(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(3,3).setOwner(5);
        assertEquals(s.checkEnd(6),5);
    }
    @Test
    void End6v3(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(1,3).setOwner(4);
        assertEquals(s.checkEnd(6),4);
    }
    @Test
    void End6v4(){
        Board b = new Board(1);
        Game g = new Game(1);
        Finish s = new Finish(b,1,g);
        b.getField(3,0).setOwner(1);
        assertEquals(s.checkEnd(6),1);
    }
    @Test
    void Run6(){
        Board b = new Board(4);
        Game g = new Game(4);
        Finish s = new Finish(b,4,g);
        assertEquals(s.checkEnd(6),-1);

    }
    @Test
    void Run4(){
        Board b = new Board(4);
        Game g = new Game(4);
        Finish s = new Finish(b,4,g);
        assertEquals(s.checkEnd(4),-1);

    }
    @Test
    void Run3(){
        Board b = new Board(4);
        Game g = new Game(4);
        Finish s = new Finish(b,4,g);
        assertEquals(s.checkEnd(3),-1);

    }
}