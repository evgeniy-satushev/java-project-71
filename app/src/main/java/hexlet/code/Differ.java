package hexlet.code;

import hexlet.code.formatter.OutputFormatData;

import java.util.Map;

public class Differ {
    public static String generate(String format, Map<String, Object> firstFile, Map<String, Object> secondFile) {
        if (format.equals("plain")) {
            return OutputFormatData.generateDiff(format, firstFile, secondFile);
        }
        return "{\n" + OutputFormatData.generateDiff(format, firstFile, secondFile) + "}";
    }
}
