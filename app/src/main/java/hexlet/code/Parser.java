package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> getData(final Path content) throws IOException {
        return parse(String.valueOf(content));
    }
    private static Map<String, Object> parse(final String pathToFile) throws IOException {
        Map<String, Object> parsedData;
        if (pathToFile.endsWith(".yml")) {
            parsedData = new ObjectMapper(new YAMLFactory())
                    .findAndRegisterModules()
                    .readValue(new File(pathToFile), new TypeReference<>() { });
            return new TreeMap<>(parsedData);
        }
        parsedData = new ObjectMapper().readValue(new File(pathToFile), new TypeReference<>() { });
        return new TreeMap<>(parsedData);
    }
}
