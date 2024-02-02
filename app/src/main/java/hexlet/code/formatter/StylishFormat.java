package hexlet.code.formatter;

import java.util.Formatter;
import java.util.Map;

public class StylishFormat {
    static String getStylishFormatData(String sign, String key, Object value) {
        Map<String, String> signOfDifference = Map.of(
                "added", "+",
                "removed", "-",
                "unchanged", " ");
        return new Formatter()
                .format("  %s %s: %s\n", signOfDifference.get(sign), key, value)
                .toString();
    }
}
