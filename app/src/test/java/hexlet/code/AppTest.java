package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    Path filepathYaml1 = Paths.get("./src/test/resources/test-file1.yml");
    Path filepathYaml2 = Paths.get("./src/test/resources/test-file2.yml");
    Path filepathYaml3 = Paths.get("./src/test/resources/big-file1.yml");
    Path filepathYaml4 = Paths.get("./src/test/resources/big-file2.yml");
    Path filepathJson1 = Paths.get("./src/test/resources/test-file1.json");
    Path filepathJson2 = Paths.get("./src/test/resources/test-file2.json");
    Path filepathJson3 = Paths.get("./src/test/resources/big-file1.json");
    Path filepathJson4 = Paths.get("./src/test/resources/big-file2.json");
    Path emptyFile = Paths.get("./src/test/resources/empty-file.json");
//    Path nonExistedFilepath = Paths.get("./src/test/resources/non-existed.json");
    @Test
    void generateTest() {
        String formatStylish = "default";
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
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }""";

        String expected1 = "";
        String expected2 = "";
        String expected3 = "";
        String expected4 = "";
        String expected5 = "";
        try {
            expected1 = Differ.generate(formatStylish, Parser.getData(filepathYaml1), Parser.getData(filepathYaml2));
            expected2 = Differ.generate(formatStylish, Parser.getData(filepathJson1), Parser.getData(filepathJson2));
            expected3 = Differ.generate(formatStylish, Parser.getData(emptyFile), Parser.getData(emptyFile));
            expected4 = Differ.generate(formatStylish, Parser.getData(filepathJson3), Parser.getData(filepathJson4));
            expected5 = Differ.generate(formatStylish, Parser.getData(filepathYaml3), Parser.getData(filepathYaml4));
        } catch (IOException e) {
            System.out.println("INCORRECT PATH TO FILE");
        }
        assertEquals(actual, expected1);
        assertEquals(actual, expected2);
        assertEquals(actual2, expected3);
        assertEquals(actual3, expected4);
        assertEquals(actual3, expected5);
    }
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
