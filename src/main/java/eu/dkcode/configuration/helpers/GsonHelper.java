package eu.dkcode.configuration.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.dkcode.configuration.adapters.ExclusionAdapter;
import eu.dkcode.configuration.adapters.LongAdapter;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class GsonHelper
 **/

public class GsonHelper {

     private final GsonBuilder gson = new Gson().newBuilder()
            // other
            .registerTypeHierarchyAdapter(Long.class, new LongAdapter())
            .setExclusionStrategies(new ExclusionAdapter())

            .setPrettyPrinting()
            .serializeNulls();

    public GsonBuilder getGson() {
        return gson;
    }
}
