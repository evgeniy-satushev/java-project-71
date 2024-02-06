package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Converter {

    public static String getJsonFormat(Map<String, Object> temp) throws JsonProcessingException {
        return convertToJsonFormat(temp);
    }
    private static String convertToJsonFormat(Map<String, Object> temp) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(temp);
    }
}
