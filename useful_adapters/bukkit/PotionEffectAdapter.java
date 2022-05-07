import eu.dkcode.gson.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.lang.reflect.Type;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 08.05.2022
 * @Class PotionEffect
 **/

public class PotionEffectAdapter implements JsonDeserializer<PotionEffect>, JsonSerializer<PotionEffect> {
    @Override
    public PotionEffect deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        PotionEffectType potionType = PotionEffectType.getByName(object.get("potionType").getAsString());
        int duration = object.get("duration").getAsInt();
        int amplifier = object.get("amplifier").getAsInt();
        boolean ambient = object.get("ambient").getAsBoolean();

        return new PotionEffect(potionType, duration, amplifier, ambient);
    }

    @Override
    public JsonElement serialize(PotionEffect potionType, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("potionType", potionType.getType().getName());
        object.addProperty("duration", potionType.getDuration());
        object.addProperty("amplifier", potionType.getAmplifier());
        object.addProperty("ambient", potionType.isAmbient());
        return object;
    }
}
