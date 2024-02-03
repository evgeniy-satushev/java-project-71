package hexlet.code.formatter;


import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputFormatData {
    public static String generateDiff(String format, Map<String, Object> firstFile, Map<String, Object> secondFile) {
        Set<String> keys = new HashSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        return keys.stream()
                .sorted(Comparator.naturalOrder())
                .map(key -> {
                    if (!firstFile.containsKey(key)) {
                        return OutputFormatData.getDiffData(format, "added", key, secondFile.get(key));
                    } else if (!secondFile.containsKey(key)) {
                        return OutputFormatData.getDiffData(format, "removed", key, firstFile.get(key));
                    } else if (isNullValue(secondFile.get(key), firstFile.get(key))) {
                        return OutputFormatData.getDiffData(format, "unchanged", key, secondFile.get(key));
                    } else {
                        return OutputFormatData.getDiffData(format, "updated", key,
                                firstFile.get(key), secondFile.get(key));
                    }
                }).collect(Collectors.joining(""));
    }
    private static String getDiffData(String format, String sign, String key, Object value1, Object value2) {
        if (format.equalsIgnoreCase("plain")) {
            return PlainFormat.getPlainFormatData(sign, key, value1, value2);
        } else {
            return StylishFormat.getStylishFormatData("removed", key, value1)
                    + StylishFormat.getStylishFormatData("added", key, value2);
        }
    }
    private static String getDiffData(String format, String sign, String key, Object value1) {
        if (sign.equals("updated")) {
            return getDiffData(format, sign, key, value1, "");
        }
        if (format.equalsIgnoreCase("plain")) {
            return PlainFormat.getPlainFormatData(sign, key, value1, "");
        } else {
            return StylishFormat.getStylishFormatData(sign, key, value1);
        }
    }
    private static boolean isNullValue(Object value1, Object value2) {
        if (value2 != null && value1 != null) {
            return value2.equals(value1);
        }
        return value1 == null && value2 == null;
    }
}
