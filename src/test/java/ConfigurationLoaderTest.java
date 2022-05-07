import eu.dkcode.configuration.ConfigLoader;
import eu.dkcode.configuration.annotations.ConfigurationExclusion;
import eu.dkcode.configuration.annotations.ConfigurationFile;
import eu.dkcode.configuration.exceptions.UnAnnotatedException;

import java.io.IOException;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class ConfigurationLoaderTest
 **/

public class ConfigurationLoaderTest {

    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();

        try {
            Configuration configuration = configLoader.load(
                    Configuration.class,
                    new Configuration()
            );

            System.out.println(configuration.name);
            System.out.println(configuration.longValue);
            System.out.println(configuration.value);

            configuration.name = "save_test";
            configLoader.save(Configuration.class, configuration);


        } catch (UnAnnotatedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    @ConfigurationFile(directory = "src/test/resources/config/test", name = "configuration.json")
    static class Configuration {
        private String name;
        @ConfigurationExclusion
        private String value;
        private Long longValue;

        public Configuration() {
            this.name = "string";
            this.value = "test";
            this.longValue = 1L;
        }
    }
}
