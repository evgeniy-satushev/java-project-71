package hexlet.code.formatter;

import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Map;

public class PlainFormat {
    //     Property 'chars2' was updated. From [complex value] to false
//     Property 'checked' was updated. From false to true
//     Property 'default' was updated. From null to [complex value]
//     Property 'id' was updated. From 45 to null
//     Property 'key1' was removed
//     Property 'key2' was added with value: 'value2'
//     Property 'numbers2' was updated. From [complex value] to [complex value]
//     Property 'numbers3' was removed
//     Property 'numbers4' was added with value: [complex value]
//     Property 'obj1' was added with value: [complex value]
//     Property 'setting1' was updated. From 'Some value' to 'Another value'
//     Property 'setting2' was updated. From 200 to 300
//     Property 'setting3' was updated. From true to 'none'
    static String getPlainFormatData(String sign, String key, Object value1, Object value2) {
        Formatter formatter = new Formatter();
        Object complexValue = isComplexObject(value1) ? "[complex value]" : value1;
        switch (sign) {
            case "removed" -> formatter.format("Property '%s' was removed\n", key);
            case "added" -> {
                if (isComplexObject(value1) || isWrapperObject(value1)) {
                    formatter.format("Property '%s' was added with value: %s\n", key, complexValue);
                } else {
                    formatter.format("Property '%s' was added with value: '%s'\n", key, value1);
                }
            }
            case "updated" -> {
                Object complexVal1 = isComplexObject(value1) ? "[complex value]" : value1;
                Object complexVal2 = isComplexObject(value2) ? "[complex value]" : value2;
                if ((isComplexObject(value1) || isWrapperObject(value1))
                        && !(isComplexObject(value2) || isWrapperObject(value2))
                        && !isNull(value2)) {
                    formatter.format("Property '%s' was updated. From %s to '%s'\n", key, complexVal1, complexVal2);
                } else if (!(isComplexObject(value1) || isWrapperObject(value1))
                        && (isComplexObject(value2) || isWrapperObject(value2))
                        && !isNull(value1)) {
                    formatter.format("Property '%s' was updated. From '%s' to %s\n", key, complexVal1, complexVal2);
                } else if (!(isComplexObject(value1) || isWrapperObject(value1))
                        && !(isComplexObject(value2) || isWrapperObject(value2))
                        && !(isNull(value1) && isNull(value2))) {
                    formatter.format("Property '%s' was updated. From '%s' to '%s'\n", key, complexVal1, complexVal2);
                } else {
                    formatter.format("Property '%s' was updated. From %s to %s\n", key, complexVal1, complexVal2);
                }
            }
            default -> System.out.println("?");
        }
//        formatter.format("Property '%s' was updated. From %s to '%s'\n", key, complexValue1, complexValue2);
        return formatter.toString();
    }
    public static boolean checkCompexData(Object value1, Object value2) {
        return (isComplexObject(value1) || isWrapperObject(value1))
                && !(isComplexObject(value2) || isWrapperObject(value2))
                && !isNull(value2);
    }
    private static boolean isComplexObject(Object value) {
        return value instanceof Arrays || value instanceof List || value instanceof Map;
    }
    private static boolean isWrapperObject(Object value) {
        return value instanceof Boolean || value instanceof Integer;
    }
    private static boolean isNull(Object value) {
        return value == null;
    }
}
