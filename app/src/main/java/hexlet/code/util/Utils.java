package hexlet.code.util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Utils {
    public static boolean isNullValue(Object value1, Object value2) {
        if (value2 != null && value1 != null) {
            return value2.equals(value1);
        }
        return value1 == null && value2 == null;
    }
    public static Object complexObject(Object value) {
        return isComplexObject(value) ? "[complex value]" : value;
    }
    public static boolean isString(Object value) {
        return value instanceof String;
    }
    public static boolean isComplexObject(Object value) {
        return value instanceof Arrays || value instanceof List || value instanceof Map;
    }
}
