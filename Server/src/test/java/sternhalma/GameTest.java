package sternhalma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.io.*;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class GameTest extends StandardTest{
    final Socket socket1 = mock(Socket.class);
    final OutputStream of1 = mock(OutputStream.class);
    final InputStream in1 = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
    final Socket socket2 = mock(Socket.class);
    final OutputStream of2 = mock(OutputStream.class);
    final InputStream in2 = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
    final Socket socket3 = mock(Socket.class);
    final OutputStream of3 = mock(OutputStream.class);
    final InputStream in3 = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
    ExecutorService pool = Executors.newFixedThreadPool(200);

    @BeforeEach
    void setup() {
        try {
            when(socket1.getOutputStream()).thenReturn(of1);
            when(socket1.getInputStream()).thenReturn(in1);
            when(socket2.getOutputStream()).thenReturn(of2);
            when(socket2.getInputStream()).thenReturn(in2);
            when(socket3.getOutputStream()).thenReturn(of3);
            when(socket3.getInputStream()).thenReturn(in3);
            when(in1.read()).thenReturn(0);
            when(in2.read()).thenReturn(0);
            when(in3.read()).thenReturn(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @AfterEach
    void end() {
        try {
            when(in1.read()).thenReturn(-1);
            when(in2.read()).thenReturn(-1);
            when(in3.read()).thenReturn(-1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.shutdownNow();
    }
    @Test
    void startGame() {
        Game game = new Game(4);
        synchronized (this) {
        pool.execute(game.createPlayer(socket1,0));
        pool.execute(game.createPlayer(socket2,1));
        }
        try {
            synchronized (this){
                wait(100);
                game.start();
            }
        } catch (CannotStartGameException | InterruptedException e) {
            System.out.println(game.numPlayers());
            e.printStackTrace();
        }
    }
    @Test
    void joinStartedGame() {
        Game game = new Game(4);
        pool.execute(game.createPlayer(socket1,0));
        pool.execute(game.createPlayer(socket2,1));
        try {
            synchronized (this){
                wait(100);
                game.start();
                NullPointerException e = assertThrows(NullPointerException.class,()->pool.execute(game.createPlayer(socket3,2)));;

            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void startStartedGame() {
        Game game = new Game(4);
        pool.execute(game.createPlayer(socket1,0));
        assertEquals(game.canJoin(),true);
        pool.execute(game.createPlayer(socket2,1));
        try {
            synchronized (this){
                wait(100);
                game.start();
                assertEquals(game.canJoin(),false);
                CannotStartGameException e = assertThrows(CannotStartGameException.class,()->game.start());;

            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Move() {
        Game game = new Game(1);
        Player p1 = game.createPlayer(socket1,0);
        pool.execute(p1);
        Player p2 = game.createPlayer(socket2,1);
        pool.execute(p2);
        try {
            synchronized (this){
                wait(100);
                game.start();
                try {
                    game.move(p1,0,0,1,1);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                } catch (InvalidPlayerException e) {
                    e.printStackTrace();
                }
                assertThrows(InvalidPlayerException.class,()->game.move(p1,0,0,1,1));

            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Switch() {
        Game game = new Game(1);
        Player p1 = game.createPlayer(socket1, 0);
        pool.execute(p1);
        Player p2 = game.createPlayer(socket2, 1);
        pool.execute(p2);
        try {
            synchronized (this) {
                wait(100);
                game.start();
                try {
                    game.switchPlayer(p1);
                } catch (InvalidPlayerException e) {
                    e.printStackTrace();
                }
                assertThrows(InvalidPlayerException.class, () -> game.switchPlayer(p1));

            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Win() {
        Game game = new Game(1);
        Player p1 = game.createPlayer(socket1, 0);
        pool.execute(p1);
        Player p2 = game.createPlayer(socket2, 1);
        pool.execute(p2);
        try {
            synchronized (this) {
                wait(100);
                game.start();
                try {
                    game.move(p1,0,0,1,1);
                    game.switchPlayer(p1);
                    game.move(p2,4,0,3,2);
                    game.switchPlayer(p2);
                    game.move(p1,1,1,2,1);
                    game.switchPlayer(p1);
                    game.switchPlayer(p2);
                    game.move(p1,2,1,4,0);
                    Field running = Game.class.getDeclaredField("running");
                    running.setAccessible(true);
                    assertEquals(false, (boolean) running.get(game));
                } catch (InvalidPlayerException e) {
                    e.printStackTrace();
                    assertEquals(0,1);
                } catch (InvalidMoveException e) {
                    e.printStackTrace();
                    assertEquals(0,1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


            }
        } catch (CannotStartGameException | InterruptedException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    @Test
    void Left() {
        Game game = spy(new Game(1));
        Player p1 = game.createPlayer(socket1, 0);
        pool.execute(p1);
        Player p2 = game.createPlayer(socket2, 1);
        pool.execute(p2);
        try {
            synchronized (this) {
                wait(100);
                game.leave(p2);
                verify(game).sendNames();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void MoveError() {
        Game game = new Game(1);
        Player p1 = game.createPlayer(socket1, 0);
        pool.execute(p1);
        Player p2 = game.createPlayer(socket2, 1);
        pool.execute(p2);
        try {
            synchronized (this) {
                wait(100);
                game.start();
                try {
                    game.switchPlayer(p1);
                } catch (InvalidPlayerException e) {
                    e.printStackTrace();
                }
                assertThrows(InvalidPlayerException.class, () -> game.move(p1,0,0,0,1));

            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}