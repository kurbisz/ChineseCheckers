package sternhalma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserTest extends StandardTest{

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
    void processWatch() {
        setup("WATCH\nNAME KW");
        Server s = mock(Server.class);
        User u = new User(socket1,s);
        pool.execute(u);
        try {
            synchronized (this) {
                wait(100);
                verify(s).watch(u);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void processJoin() {
        setup("JOIN\nNAME KW");
        Server s = mock(Server.class);
        User u = new User(socket1,s);
        pool.execute(u);
        try {
            synchronized (this) {
                wait(100);
                verify(s).join(u);
                assertEquals(u.getSocket(), socket1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    void processNone() {
        setup("JIN\nNAME KW");
        Server s = mock(Server.class);
        User u = new User(socket1,s);
        pool.execute(u);
        try {
            synchronized (this) {
                wait(100);
                verify(socket1).close();
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }


}