package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;



public class JsonObjConv {

    public static String ERR_STRING = "ERR";

    public static <T> T JsonStringToObj(String jsonString, Class<T> valueType) {
        if (notValid(jsonString)) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);;
            try {
                return objectMapper.readValue(jsonString, valueType);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static boolean notValid(String jsonString) {
        return jsonString == null || jsonString.isEmpty();
    }

    public static String ObjToJsonString(Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
           return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ERR_STRING;
        }
    }
}
