import eu.dkcode.gson.*;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 08.05.2022
 * @Class ItemStackAdapter
 **/

public class ItemStackAdapter implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack> {

    /*
      Deserialize ItemStack from JSON
     */

    @Override
    public ItemStack deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        ItemStack itemStack;
        JsonObject object = jsonElement.getAsJsonObject();

        itemStack = new ItemStack(
                Material.valueOf(object.get("type").getAsString()),
                object.get("amount").getAsInt(),
                object.get("durability").getAsShort(),
                object.get("data").getAsByte()
        );

        if(object.has("itemMeta")) itemStack.setItemMeta(deserializeItemMeta(object.get("itemMeta").getAsJsonObject(), itemStack));

        if(object.has("enchantments")) itemStack.addUnsafeEnchantments(deserializeEnchantments(object.get("enchantments").getAsJsonObject()));

        return itemStack;
    }

    private Map<Enchantment, Integer> deserializeEnchantments(JsonObject enchantments) {
        Map<Enchantment, Integer> map = new java.util.HashMap<>();

        for(String key : enchantments.keySet()) {
            Enchantment enchantment = Enchantment.getByName(key);
            if(enchantment != null) map.put(enchantment, enchantments.get(key).getAsInt());
        }

        return map;
    }

    private ItemMeta deserializeItemMeta(JsonObject itemMeta, ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();

        if(itemMeta.has("displayName")) meta.setDisplayName(itemMeta.get("displayName").getAsString());
        if(itemMeta.has("lore")) meta.setLore(deserializeLore(itemMeta.get("lore").getAsJsonArray()));

        if(itemMeta.has("owner")) {
            SkullMeta skullMeta = (SkullMeta) meta;
            skullMeta.setOwner(itemMeta.get("owner").getAsString());
        }

        return meta;
    }

    private List<String> deserializeLore(JsonArray lore) {
        List<String> list = new java.util.ArrayList<>();

        for (JsonElement element : lore)
            list.add(element.getAsString());

        return list;
    }

    /*
     Serializing ItemStack to JSON
     */

    @Override
    public JsonElement serialize(ItemStack itemStack, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();

        object.addProperty("type", itemStack.getType().name());
        object.addProperty("amount", itemStack.getAmount());
        object.addProperty("durability", itemStack.getDurability());
        object.addProperty("data", itemStack.getData().getData());

        if(itemStack.hasItemMeta()) object.add("itemMeta", serializeItemMeta(itemStack.getItemMeta()));

        if(itemStack.getEnchantments().size() > 0) object.add("enchantments", serializeEnchantments(itemStack.getEnchantments()));

        return object;
    }

    private JsonElement serializeEnchantments(Map<Enchantment, Integer> enchantments) {
        JsonObject object = new JsonObject();

        for (Enchantment enchantment : enchantments.keySet())
            object.addProperty(enchantment.getName(), enchantments.get(enchantment));

        return object;
    }

    private JsonObject serializeItemMeta(ItemMeta itemMeta) {
        JsonObject object = new JsonObject();

        if(itemMeta.hasDisplayName())
            object.addProperty("displayName", itemMeta.getDisplayName());

        if(itemMeta.hasLore())
            object.add("lore", serializeLore(itemMeta.getLore()));

        if(itemMeta instanceof SkullMeta){
            SkullMeta skullMeta = (SkullMeta) itemMeta;

            if(skullMeta.hasOwner())
                object.addProperty("owner", skullMeta.getOwner());

        }

        return object;
    }

    private JsonElement serializeLore(List<String> lore) {
        JsonArray array = new JsonArray();

        for(String line : lore)
            array.add(new JsonPrimitive(line));

        return array;
    }

}
