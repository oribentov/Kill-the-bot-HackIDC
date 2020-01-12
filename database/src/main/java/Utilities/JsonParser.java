package Utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class JsonParser {

    private  static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static JsonNode StringToJson(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonNode ObjectToJson (Object obj) {
        try {
            return  StringToJson(mapper.writeValueAsString(obj));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T JsonToObject (JsonNode json, Class<T> objClass) {
        try {
            return mapper.readValue(json.toString(), objClass);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
