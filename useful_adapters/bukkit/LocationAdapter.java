
import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.lang.reflect.Type;


public class LocationAdapter implements JsonDeserializer<Location>, JsonSerializer<Location> {

    @Override
    public Location deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return fromJson(jsonElement);
    }

    @Override
    public JsonElement serialize(Location location, Type type, JsonSerializationContext jsonSerializationContext) {
        return toJson(location);
    }

    public static JsonObject toJson(Location location) {
        final JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("world", location.getWorld().getName());
        jsonObject.addProperty("x", location.getX());
        jsonObject.addProperty("y", location.getY());
        jsonObject.addProperty("z", location.getZ());
        jsonObject.addProperty("yaw", location.getYaw());
        jsonObject.addProperty("pitch", location.getPitch());

        return jsonObject;
    }


    public static Location fromJson(JsonElement jsonElement) {
        if (jsonElement == null || !jsonElement.isJsonObject()) return null;

        final JsonObject jsonObject = jsonElement.getAsJsonObject();

        return new Location(
                Bukkit.getWorld(jsonObject.get("world").getAsString()),
                jsonObject.get("x").getAsDouble(),
                jsonObject.get("y").getAsDouble(),
                jsonObject.get("z").getAsDouble(),
                jsonObject.get("yaw").getAsFloat(),
                jsonObject.get("pitch").getAsFloat()
        );
    }
}