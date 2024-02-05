package hexlet.code;

import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void mainTest() {
        int actual1 = 1;
        int expected1 = new CommandLine(new App()).execute("file1.json", "file2.json");
        assertEquals(actual1, expected1);

        int actual2 = 0;
        int expected2 = new CommandLine(new App()).execute("-V");
        assertEquals(actual2, expected2);
    }
}
