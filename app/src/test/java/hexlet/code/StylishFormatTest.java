package hexlet.code;

import hexlet.code.formatter.StylishFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StylishFormatTest {

    @Test
    void getStylishFormatDataTest() {
        Map<String, Object> testObject1 = Map.of(
                "setting1", "Some value",
                "setting2", 200,
                "setting3", true,
                "key1", "value1",
                "numbers1", new int[]{1, 2, 3, 4});

        String actual1 = "  - setting1: Some value\n";
        String expected1 = StylishFormat.getStylishFormatData("removed", "setting1", testObject1.get("setting1"));
        Assertions.assertEquals(actual1, expected1);
    }
}
