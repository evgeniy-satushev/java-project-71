package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatter.OutputFormatData;

import java.util.Map;

public class Differ {
    public static String generate(Map<String, Object> firstFile, Map<String, Object> secondFile, String format)
            throws JsonProcessingException {
        if (!format.equalsIgnoreCase("json")) {
            return OutputFormatData.getSimplyFormat(format, firstFile, secondFile);
        } else {
            firstFile.putAll(secondFile);
            return Converter.getJsonFormat(firstFile);
        }
    }
}
