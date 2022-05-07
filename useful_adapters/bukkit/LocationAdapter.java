import eu.dkcode.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 08.05.2022
 * @Class Location
 **/

public class LocationAdapter implements JsonDeserializer<Location>, JsonSerializer<Location> {
    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new Location(
                Bukkit.getWorld(jsonObject.get("world").getAsString()),
                jsonObject.get("x").getAsDouble(),
                jsonObject.get("y").getAsDouble(),
                jsonObject.get("z").getAsDouble(),
                jsonObject.get("yaw").getAsFloat(),
                jsonObject.get("pitch").getAsFloat()
        );
    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("world", location.getWorld().getName());
        object.addProperty("x", location.getX());
        object.addProperty("y", location.getY());
        object.addProperty("z", location.getZ());
        object.addProperty("yaw", location.getYaw());
        object.addProperty("pitch", location.getPitch());

        return object;
    }
}
