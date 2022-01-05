package sternhalma.board;

import org.junit.jupiter.api.Test;
import sternhalma.Game;

import static org.junit.jupiter.api.Assertions.*;

class ClassicStartTest {

    @Test
    void Generate2(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicStart s = new ClassicStart(b,4,g);
        s.prepare(2);
        assertEquals(b.getField(0,0).getOwner(),0);
        assertEquals(b.getField(16,0).getOwner(),1);
    }
    @Test
    void Generate6(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicStart s = new ClassicStart(b,4,g);
        s.prepare(6);
        assertEquals(b.getField(0,0).getOwner(),0);
        assertEquals(b.getField(16,0).getOwner(),3);
        assertEquals(b.getField(4,0).getOwner(),5);
        assertEquals(b.getField(4,12).getOwner(),1);
        assertEquals(b.getField(12,0).getOwner(),4);
        assertEquals(b.getField(12,12).getOwner(),2);
    }
    @Test
    void Generate4(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicStart s = new ClassicStart(b,4,g);
        s.prepare(4);
        assertEquals(b.getField(0,0).getOwner(),0);
        assertEquals(b.getField(16,0).getOwner(),2);
        assertEquals(b.getField(4,0).getOwner(),3);
        assertEquals(b.getField(12,12).getOwner(),1);
    }
    @Test
    void Generate3(){
        ClassicBoard b = new ClassicBoard(4, new ClassicMove());
        Game g = new Game(4);
        ClassicStart s = new ClassicStart(b,4,g);
        s.prepare(3);
        assertEquals(b.getField(0,0).getOwner(),0);
        assertEquals(b.getField(12,0).getOwner(),2);
        assertEquals(b.getField(12,12).getOwner(),1);
    }
}