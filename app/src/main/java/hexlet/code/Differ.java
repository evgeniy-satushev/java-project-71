package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(Map<String, Object> firstFile, Map<String, Object> secondFile) {
        Set<String> keys = new HashSet<>(firstFile.keySet());
        keys.addAll(secondFile.keySet());
        String stylishFormat = keys.stream()
                .sorted(Comparator.naturalOrder())
                .map(key -> {
                    if (!firstFile.containsKey(key)) {
                        return getMappedObject("+ ", key, secondFile.get(key));
                    } else if (!secondFile.containsKey(key)) {
                        return getMappedObject("- ", key, firstFile.get(key));
                    } else if (secondFile.get(key).equals(firstFile.get(key))) {
                        return getMappedObject("  ", key, secondFile.get(key));
                    } else {
                        return getMappedObject("- ", key, firstFile.get(key))
                            + getMappedObject("+ ", key, secondFile.get(key));
                    }
                }).collect(Collectors.joining(""));
        return "{\n" + stylishFormat + "}";
    }
    public static Map<String, Object> getData(final Path content) throws IOException {
        return parse(String.valueOf(content));
    }
    private static Map<String, Object> parse(final String pathToFile) throws IOException {
        return new ObjectMapper().readValue(new File(pathToFile), new TypeReference<>() { });
    }
    private static String getMappedObject(String sign, String key, Object value) {
        return "  " + sign + key + ": " + value + "\n";
    }
}
