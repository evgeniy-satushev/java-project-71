package hexlet.code;

import hexlet.code.formatter.PlainFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PlainFormatTest {
    @Test
    void getPlainFormatDataTest() {
        Map<String, Object> testObject1 = Map.of(
                "setting1", "Some value",
                "setting2", 200,
                "setting3", true,
                "key1", "value1",
                "numbers1", new int[]{1, 2, 3, 4});
        Map<String, Object> testObject2 = Map.of(
                "setting1", "Another value",
                "setting2", 300,
                "setting3", "none",
                "key2", "value2",
                "numbers1", new int[]{1, 2, 3, 4});

        String actual = "Property 'setting1' was updated. From 'Some value' to 'Another value'\n";
        String expected = PlainFormat.getPlainFormatData("updated", "setting1",
                testObject1.get("setting1"), testObject2.get("setting1"));
        Assertions.assertEquals(actual, expected);

        String actual1 = "Property 'key1' was removed\n";
        String expected1 = PlainFormat.getPlainFormatData("removed", "key1",
                testObject1.get("key1"), "");
        Assertions.assertEquals(actual1, expected1);

        String actual2 = "Property 'key2' was added with value: 'value2'\n";
        String expected2 = PlainFormat.getPlainFormatData("added", "key2",
                testObject2.get("key2"), "");
        Assertions.assertEquals(actual2, expected2);

        String actual3 = "";
        String expected3 = PlainFormat.getPlainFormatData("some", "numbers1",
                testObject1.get("numbers1"), testObject2.get("numbers1"));
        Assertions.assertEquals(actual3, expected3);
    }
}
