package hexlet.code.formatter;


import hexlet.code.util.Utils;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputFormatData {
    public static String getSimplyFormat(String format, Map<String, Object> firstFile, Map<String, Object> secondFile) {
        if (format.equalsIgnoreCase("stylish")) {
            return "{\n" + generateDiff(format, firstFile, secondFile) + "}";
        } else {
            return generateDiff(format, firstFile, secondFile);
        }
    }
    private static String generateDiff(String format, Map<String, Object> firstFile, Map<String, Object> secondFile) {
        Set<String> keys = new HashSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        return keys.stream()
                .sorted(Comparator.naturalOrder())
                .map(key -> {
                    if (!firstFile.containsKey(key)) {
                        return OutputFormatData.getDiffLineData(format, "added", key, secondFile.get(key));
                    } else if (!secondFile.containsKey(key)) {
                        return OutputFormatData.getDiffLineData(format, "removed", key, firstFile.get(key));
                    } else if (Utils.isNullValue(secondFile.get(key), firstFile.get(key))) {
                        return OutputFormatData.getDiffLineData(format, "unchanged", key, secondFile.get(key));
                    } else {
                        return OutputFormatData.getDiffLineData(format, "updated", key,
                                firstFile.get(key), secondFile.get(key));
                    }
                }).collect(Collectors.joining(""));
    }
    private static String getDiffLineData(String format, String sign, String key, Object value1, Object value2) {
        if (format.equalsIgnoreCase("plain")) {
            return PlainFormat.getPlainFormatData(sign, key, value1, value2);
        } else {
            return StylishFormat.getStylishFormatData("removed", key, value1)
                    + StylishFormat.getStylishFormatData("added", key, value2);
        }
    }
    private static String getDiffLineData(String format, String sign, String key, Object value1) {
        if (sign.equals("updated")) {
            return getDiffLineData(format, sign, key, value1, "");
        }
        if (format.equalsIgnoreCase("plain")) {
            return PlainFormat.getPlainFormatData(sign, key, value1, "");
        } else {
            return StylishFormat.getStylishFormatData(sign, key, value1);
        }
    }
}
