package eu.dkcode.configuration.adapters;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class LongAdapter
 **/

public class LongAdapter implements JsonDeserializer<Long>, JsonSerializer<Long> {
    @Override
    public Long deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return Long.parseLong(jsonElement.getAsString());
    }

    @Override
    public JsonElement serialize(Long aLong, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(String.valueOf(aLong));
    }
}