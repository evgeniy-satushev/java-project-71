package hexlet.code.formatter;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class PlainFormat {
    public static String getPlainFormatData(String sign, String key, Object value1, Object value2) {
        Formatter formatter = new Formatter();
        Object val1 = complexObject(value1);
        Object val2 = complexObject(value2);
        switch (sign) {
            case "removed" -> formatter.format("Property '%s' was removed\n", key);
            case "added" -> {
                if (!isString(value1)) {
                    formatter.format("Property '%s' was added with value: %s\n", key, val1);
                } else {
                    formatter.format("Property '%s' was added with value: '%s'\n", key, value1);
                }
            }
            case "updated" -> {
                if (isString(value1) && isString(value2)) {
                    formatter.format("Property '%s' was updated. From '%s' to '%s'\n", key, val1, val2);
                } else if (!isString(value1) && isString(value2)) {
                    formatter.format("Property '%s' was updated. From %s to '%s'\n", key, val1, val2);
                } else if (isString(value1) && !isString(value2)) {
                    formatter.format("Property '%s' was updated. From '%s' to %s\n", key, val1, val2);
                } else {
                    formatter.format("Property '%s' was updated. From %s to %s\n", key, val1, val2);
                }
            }
            default -> formatter.format("");
        }
        return formatter.toString();
    }
    private static Object complexObject(Object value) {
        return isComplexObject(value) ? "[complex value]" : value;
    }
    private static boolean isString(Object value) {
        return value instanceof String;
    }
    private static boolean isComplexObject(Object value) {
        return value instanceof Arrays || value instanceof List || value instanceof Map;
    }
}
