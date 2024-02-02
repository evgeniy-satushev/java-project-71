package hexlet.code;

import hexlet.code.formatter.OutputFormatData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String format, Map<String, Object> firstFile, Map<String, Object> secondFile) {
        Set<String> keys = new HashSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        String formattedOutput = keys.stream()
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
        if (format.equals("plain")) {
            return formattedOutput;
        }
        return "{\n" + formattedOutput + "}";
    }
    private static boolean isNullValue(Object value1, Object value2) {
        if (value2 != null && value1 != null) {
            return value2.equals(value1);
        }
        return value1 == null && value2 == null;
    }
}
