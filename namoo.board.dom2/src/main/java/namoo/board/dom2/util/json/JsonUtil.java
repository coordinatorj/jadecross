/*
 * COPYRIGHT (c) Nextree Consulting 2015
 * This software is the proprietary of Nextree Consulting.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2015. 1. 5.
 */
package namoo.board.dom2.util.json;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class JsonUtil {
    //    
    public static String toJson(Object object) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, getJsonDateSerializer())
            .registerTypeAdapter(Date.class, getJsonDateDeserializer())
            .create();
        return gson.toJson(object);
    }
    
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, getJsonDateSerializer())
            .registerTypeAdapter(Date.class, getJsonDateDeserializer())
            .create();
        
        return gson.fromJson(jsonString, clazz); 
    }
    
    public static <T> List<T> fromJsonArray(String jsonString, Class<T[]> clazz) {
        //
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, getJsonDateSerializer())
            .registerTypeAdapter(Date.class, getJsonDateDeserializer())
            .create();
        
        // fromJson으로 리스트를 변환할 경우 요소 타입이 LinkedTreeMap으로 바뀌는 문제 때문에
        // 배열로 변환 한 다음 리스트로 다시 변환
        T[] array = gson.fromJson(jsonString, clazz);
        return Arrays.asList(array);
    }
    
    private static JsonSerializer<Date> getJsonDateSerializer() {
        //
        return new JsonSerializer<Date>() {
            //
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc,
                    JsonSerializationContext context) {
                // 
                return new JsonPrimitive(src.getTime());
            }
        };
    }
    
    private static JsonDeserializer<Date> getJsonDateDeserializer() {
        //
        return new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                    JsonDeserializationContext context)
                    throws JsonParseException {
                // 
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        };
    }
}