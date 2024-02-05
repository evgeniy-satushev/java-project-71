package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Path filepathYaml1 = Paths.get("./src/test/resources/test-file1.yml");
    Path filepathYaml2 = Paths.get("./src/test/resources/test-file2.yml");
    Path filepathJson1 = Paths.get("./src/test/resources/test-file1.json");
    Path filepathJson2 = Paths.get("./src/test/resources/test-file2.json");
    Path emptyFile = Paths.get("./src/test/resources/empty-file.json");

    @Test
    void getDataTest() {
        Map<String, Object> actual = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        Map<String, Object> actual2 = Map.of("timeout", 20,
                "verbose", true,
                "host", "hexlet.io");
        Map<String, Object> actual3 = Map.of();
        Map<String, Object> expected1 = new HashMap<>();
        Map<String, Object> expected2 = new HashMap<>();
        Map<String, Object> expected3 = new HashMap<>();
        Map<String, Object> expected4 = new HashMap<>();
        Map<String, Object> expected5 = new HashMap<>();
        try {
            expected1 = Parser.getData(filepathJson1);
            expected2 = Parser.getData(filepathJson2);
            expected3 = Parser.getData(filepathYaml1);
            expected4 = Parser.getData(filepathYaml2);
            expected5 = Parser.getData(emptyFile);
        } catch (IOException e) {
            System.out.println("INCORRECT PATH TO FILE");
        }
        assertEquals(actual, expected1);
        assertEquals(actual2, expected2);
        assertEquals(actual, expected3);
        assertEquals(actual2, expected4);
        assertEquals(actual3, expected5);
    }
}
