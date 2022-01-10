package sternhalma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class created to redirect and test System.out.println output.
 */
public class StandardTest {
    protected final PrintStream standardOut = System.out;
    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    @BeforeEach
    void c() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    @AfterEach
    void cc() {
        System.setOut(standardOut);
    }
}
