package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Differ {
    public static String generate(Map<String, Object> firstFile, Map<String, Object> secondFile) {
        StringBuilder stringBuilder = new StringBuilder("{\n");
        Set<String> keys = new HashSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        for (String key : keys) {
            if (!firstFile.containsKey(key)) {
//                added
                stringBuilder
                        .append("  ")
                        .append("+ ")
                        .append(key)
                        .append(": ")
                        .append(secondFile.get(key))
                        .append("\n");
            } else if (!secondFile.containsKey(key)) {
//                deleted
                stringBuilder
                        .append("  ")
                        .append("- ")
                        .append(key)
                        .append(": ")
                        .append(firstFile.get(key))
                        .append("\n");
            } else if (secondFile.get(key).equals(firstFile.get(key))) {
//                unchanged
                stringBuilder
                        .append("  ")
                        .append("  ")
                        .append(key)
                        .append(": ")
                        .append(secondFile.get(key))
                        .append("\n");
            } else {
//                changed
                stringBuilder
                        .append("  ")
                        .append("- ")
                        .append(key)
                        .append(": ")
                        .append(firstFile.get(key))
                        .append("\n")
                        .append("  ")
                        .append("+ ")
                        .append(key)
                        .append(": ")
                        .append(secondFile.get(key))
                        .append("\n");
            }
        }
        return stringBuilder + "}";
    }
    public static Map<String, Object> getData(final Path content) throws IOException {
        return parse(String.valueOf(content));
    }
    private static Map<String, Object> parse(final String pathToFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> parsedFile = mapper.readValue(new File(pathToFile), new TypeReference<>(){});
        return new TreeMap<>(parsedFile);

    }
}
