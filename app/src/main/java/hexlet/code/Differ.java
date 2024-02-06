package hexlet.code;

import hexlet.code.formatter.OutputFormatData;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;



public class Differ {
    public static String generate(String filepath1, String filepath2, String format)
            throws IOException {
        Map<String, Object> firstFile = Parser.getData(getFilePath(filepath1));
        Map<String, Object> secondFile = Parser.getData(getFilePath(filepath2));
        if (!format.equalsIgnoreCase("json")) {
            return OutputFormatData.getSimplyFormat(format, firstFile, secondFile);
        } else {
            firstFile.putAll(secondFile);
            return Converter.getJsonFormat(firstFile);
        }
    }
    private static Path getFilePath(String filepath) {
        return Paths
                .get(filepath)
                .toAbsolutePath()
                .normalize();
    }
}
