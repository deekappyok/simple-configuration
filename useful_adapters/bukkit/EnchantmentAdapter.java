import eu.dkcode.gson.*;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Type;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 08.05.2022
 * @Class EnchantmentAdapter
 **/

public class EnchantmentAdapter implements JsonDeserializer<Enchantment>, JsonSerializer<Enchantment> {
    @Override
    public Enchantment deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String name = object.get("name").getAsString();
        return Enchantment.getByName(name);
    }

    @Override
    public JsonElement serialize(Enchantment enchantment, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("name", enchantment.getName());
        return object;
    }
}
