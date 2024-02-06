package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {

    String filepathYaml1 = ("./src/test/resources/test-file1.yml");
    String filepathYaml2 = ("./src/test/resources/test-file2.yml");
    String filepathJson1 = ("./src/test/resources/test-file1.json");
    String filepathJson2 = ("./src/test/resources/test-file2.json");
    String emptyFile = ("./src/test/resources/empty-file.json");
    @Test
    void generateTest() {
        String formatStylish = "stylish";
        String formatPlain = "plain";
        String formatJson = "json";
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
        String actual4 = "{\"follow\":false,"
                + "\"host\":\"hexlet.io\","
                + "\"proxy\":\"123.234.53.22\","
                + "\"timeout\":20,"
                + "\"verbose\":true}";

        String expected1 = "";
        String expected2 = "";
        String expected3 = "";
        String expected4 = "";
        String expected5 = "";
        String expected6 = "";
        try {
            expected1 = Differ.generate(filepathYaml1, filepathYaml2, formatStylish);
            expected2 = Differ.generate(filepathJson1, filepathJson2, formatStylish);
            expected3 = Differ.generate(emptyFile, emptyFile, formatStylish);
            expected4 = Differ.generate(filepathJson1, filepathJson2, formatPlain);
            expected5 = Differ.generate(filepathYaml1, filepathYaml2, formatPlain);
            expected6 = Differ.generate(filepathJson1, filepathJson2, formatJson);

        } catch (IOException e) {
            System.out.println("INCORRECT PATH TO FILE");
        }
        assertEquals(actual, expected1);
        assertEquals(actual, expected2);
        assertEquals(actual2, expected3);
        assertEquals(actual3, expected4);
        assertEquals(actual3, expected5);
        assertEquals(actual4, expected6);
    }
}
