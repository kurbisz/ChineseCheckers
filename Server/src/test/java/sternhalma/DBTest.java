package sternhalma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import sternhalma.database.Database;
import sternhalma.database.MySQLDatabase;
import sternhalma.exceptions.CannotPlayMove;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class DBTest extends StandardTest{
    ExecutorService pool = Executors.newFixedThreadPool(200);
    @AfterEach
    void clear() {
        pool.shutdownNow();
    }
    @Test
    void processWatch() {
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        MySQLDatabase database = (MySQLDatabase) context.getBean("db");
        try {
            synchronized (this) {
                wait(100);
                database.test();
            }
        } catch (InterruptedException e) {

        }
    }
    }