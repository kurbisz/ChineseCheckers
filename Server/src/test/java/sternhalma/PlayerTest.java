package sternhalma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import sternhalma.exceptions.CannotStartGameException;
import sternhalma.exceptions.InvalidMoveException;
import sternhalma.exceptions.InvalidPlayerException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class PlayerTest extends StandardTest{

    Socket socket1 = mock(Socket.class);
    OutputStream of1 = mock(OutputStream.class);
    InputStream in1 = new ByteArrayInputStream("START".getBytes());
    Socket socket2 = mock(Socket.class);
    OutputStream of2 = mock(OutputStream.class);
    InputStream in2 = mock(InputStream.class, Mockito.CALLS_REAL_METHODS);
    ExecutorService pool = Executors.newFixedThreadPool(200);
    @AfterEach
    void clear() {
        pool.shutdownNow();
    }
    void setup(String s) {
        try {
            in1 = new ByteArrayInputStream(s.getBytes());
            when(socket1.getOutputStream()).thenReturn(of1);
            when(socket1.getInputStream()).thenReturn(in1);
            when(socket2.getInputStream()).thenReturn(in2);
            when(socket2.getOutputStream()).thenReturn(of2);
            when(in2.read()).thenReturn(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void processStart() {
        setup("START\nNAME KW");
        Game game = spy(new Game(4));
        Player p = game.createPlayer(socket1,0);
        pool.execute(p);
        try {
            synchronized (this) {
                wait(100);
                verify(game).start();
                verify(game, times(2)).sendNames();
            }
        } catch (CannotStartGameException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void processMove() {
        setup("START\nMOVE 3 0 4 4\nMOVE 0 0 0 0");
        Game game = spy(new Game(4));
        Player p = game.createPlayer(socket1,0);
        Player p2 = game.createPlayer(socket2,1);
        pool.execute(p2);
        synchronized (this) {
            try {
                wait(100);
                pool.execute(p);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            synchronized (this) {
                wait(100);
                verify(game).move(p,3,0,4,4);
                verify(game).move(p,0,0,0,0);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        } catch (InvalidMoveException e) {
            e.printStackTrace();
        }
    }
    @Test
    void processPassLeave() {
        setup("PASS\nLEAVE");
        Game game = spy(new Game(4));
        Player p = game.createPlayer(socket1,0);
        Player p2 = game.createPlayer(socket2,1);
        pool.execute(p2);
        pool.execute(p);

        try {
            synchronized (this) {
                wait(100);
                verify(game, times(1)).switchPlayer(p);
                verify(game, times(2)).leave(p);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidPlayerException e) {
            e.printStackTrace();
        }
    }
}