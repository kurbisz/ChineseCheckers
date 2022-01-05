package sternhalma.board;

import org.junit.jupiter.api.Test;
import sternhalma.Game;

import static org.junit.jupiter.api.Assertions.*;

class ClassicFinishTest {
    @Test
    void End2(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(0,0).setOwner(1);
        assertEquals(s.checkEnd(2),1);
    }
    @Test
    void End2v2(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(4,0).setOwner(0);
        assertEquals(s.checkEnd(2),0);
    }
    @Test
    void End6(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(1,0).setOwner(2);
        assertEquals(s.checkEnd(6),2);
    }
    @Test
    void End6v2(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(3,3).setOwner(5);
        assertEquals(s.checkEnd(6),5);
    }
    @Test
    void End6v3(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(1,3).setOwner(4);
        assertEquals(s.checkEnd(6),4);
    }
    @Test
    void End6v4(){
        ClassicBoard b = new ClassicBoard(1, new ClassicMove());
        Game g = new Game(1);
        ClassicFinish s = new ClassicFinish(b,1,g);
        b.getField(3,0).setOwner(1);
        assertEquals(s.checkEnd(6),1);
    }
    @Test
    void Run6(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicFinish s = new ClassicFinish(b,4,g);
        assertEquals(s.checkEnd(6),-1);

    }
    @Test
    void Run4(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicFinish s = new ClassicFinish(b,4,g);
        assertEquals(s.checkEnd(4),-1);

    }
    @Test
    void Run3(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicFinish s = new ClassicFinish(b,4,g);
        assertEquals(s.checkEnd(3),-1);

    }
}