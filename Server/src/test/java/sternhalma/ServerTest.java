package sternhalma;

import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest extends StandardTest{
    void check(String line) {
        ByteArrayInputStream b = new ByteArrayInputStream(outputStreamCaptor.toByteArray());
        Scanner s = new Scanner(b);
        String str = s.nextLine();
        assertEquals(str, line);
    }
    @Test
    void mainSize() {
        String[] args = {"4","8080"};
        try {
            AssertionFailedError as = assertThrows(AssertionFailedError.class,() -> assertTimeoutPreemptively(Duration.ofSeconds(1),()->Server.main(args)));
            check("CONFIG: [4, 8080]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void mainNull() {
        String[] args = new String[0];
        try {
            AssertionFailedError as = assertThrows(AssertionFailedError.class,() -> assertTimeoutPreemptively(Duration.ofSeconds(1),()->Server.main(args)));
            check("CONFIG: []");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}