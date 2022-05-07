package eu.dkcode.configuration.adapters.bukkit;

import com.google.gson.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

public class PotionEffectAdapter implements JsonDeserializer<PotionEffect>, JsonSerializer<PotionEffect> {

    @Override
    public JsonElement serialize(PotionEffect src, Type typeOfSrc, JsonSerializationContext context) {
        return (toJson(src));
    }


    @Override
    public PotionEffect deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return (fromJson(json));
    }

    public static JsonObject toJson( PotionEffect potionEffect) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", potionEffect.getType().getId());
        jsonObject.addProperty("duration", potionEffect.getDuration());
        jsonObject.addProperty("amplifier", potionEffect.getAmplifier());
        jsonObject.addProperty("ambient", potionEffect.isAmbient());

        return jsonObject;
    }

    public static PotionEffect fromJson( JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) return null;

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new PotionEffect(
                PotionEffectType.getById(jsonObject.get("id").getAsInt()),
                jsonObject.get("duration").getAsInt(),
                jsonObject.get("amplifier").getAsInt(),
                jsonObject.get("ambient").getAsBoolean()
        );
    }

}