package eu.dkcode.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.dkcode.configuration.annotations.ConfigurationFile;
import eu.dkcode.configuration.exceptions.UnAnnotatedException;
import eu.dkcode.configuration.helpers.GsonHelper;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class ConfigLoader
 **/

public class ConfigLoader {

    @Getter private GsonBuilder gsonBuilder;
    private Gson gson;

    public ConfigLoader(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
        this.gson = gsonBuilder.create();
    }

    public ConfigLoader(){
        this(new GsonHelper().getGson());
    }

    public void addAdapter(Class<?> clazz, Object adapter) {
        gsonBuilder.registerTypeAdapter(clazz, adapter);
        gson = gsonBuilder.create();
    }

    public void setGsonBuilder(GsonBuilder gsonBuilder) {
        this.gsonBuilder = gsonBuilder;
        this.gson = gsonBuilder.create();
    }

    public <T> T load(Class<T> tClass, Object defaults) throws UnAnnotatedException, IOException {
        if(!tClass.isAnnotationPresent(ConfigurationFile.class))
            throw new UnAnnotatedException("Class " + tClass.getName() + " is not annotated with @ConfigurationFile");

        ConfigurationFile configurationFile = tClass.getAnnotation(ConfigurationFile.class);

        String fileDirectory = configurationFile.directory();
        String fileName = configurationFile.name();

        File file = new File(fileDirectory + "/" + fileName);

        if(file.exists())
            return gson.fromJson(new String(Files.readAllBytes(file.toPath())), tClass);

        return save(tClass, defaults);
    }

    public <T> T save(Class<T> tClass, Object defaults) throws UnAnnotatedException, IOException {
        if (!tClass.isAnnotationPresent(ConfigurationFile.class))
            throw new UnAnnotatedException("Class " + tClass.getName() + " is not annotated with @ConfigurationFile");
        ConfigurationFile configurationFile = tClass.getAnnotation(ConfigurationFile.class);

        String fileDirectory = configurationFile.directory();
        String fileName = configurationFile.name();

        File directory = new File(fileDirectory);
        if (!directory.exists())
            directory.mkdirs();

        File file = new File(directory + "/" + fileName);
        String json = gson.toJson(defaults);

        // write with utf-8 encoding
        try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
            writer.println(json);
        }

        return (T) defaults;
    }
}
