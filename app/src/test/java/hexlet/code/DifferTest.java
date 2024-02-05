package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    Path filepathYaml1 = Paths.get("./src/test/resources/test-file1.yml");
    Path filepathYaml2 = Paths.get("./src/test/resources/test-file2.yml");
    Path filepathJson1 = Paths.get("./src/test/resources/test-file1.json");
    Path filepathJson2 = Paths.get("./src/test/resources/test-file2.json");
    Path emptyFile = Paths.get("./src/test/resources/empty-file.json");
    @Test
    void generateTest() {
        String formatStylish = "stylish";
        String formatPlain = "plain";
        String actual = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        String actual2 = "{\n}";
        String actual3 = """
                Property 'follow' was removed
                Property 'proxy' was removed
                Property 'timeout' was updated. From 50 to 20
                Property 'verbose' was added with value: true
                """;

        String expected1 = "";
        String expected2 = "";
        String expected3 = "";
        String expected4 = "";
        String expected5 = "";
        try {
            expected1 = Differ.generate(formatStylish, Parser.getData(filepathYaml1), Parser.getData(filepathYaml2));
            expected2 = Differ.generate(formatStylish, Parser.getData(filepathJson1), Parser.getData(filepathJson2));
            expected3 = Differ.generate(formatStylish, Parser.getData(emptyFile), Parser.getData(emptyFile));
            expected4 = Differ.generate(formatPlain, Parser.getData(filepathJson1), Parser.getData(filepathJson2));
            expected5 = Differ.generate(formatPlain, Parser.getData(filepathYaml1), Parser.getData(filepathYaml2));
        } catch (IOException e) {
            System.out.println("INCORRECT PATH TO FILE");
        }
        assertEquals(actual, expected1);
        assertEquals(actual, expected2);
        assertEquals(actual2, expected3);
        assertEquals(actual3, expected4);
        assertEquals(actual3, expected5);
    }
}
