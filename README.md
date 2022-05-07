# Simple Configuration
A simple json configuration system built with [gson](https://github.com/google/gson).

## Setup 💻
### Using Maven
**REPOSITORY**
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**DEPENDENCY**
```xml
<dependency>
    <groupId>com.github.DeeKaPPy</groupId>
	<artifactId>simple-configuration</artifactId>
    <version>1.0-SNAPSHOT</version> <!-- or replace with newer version -->
</dependency>
```

## Usage 📚
### Initialize
```java
class Bootstrap {
    
    private final ConfigLoader loader;
    
    public Bootstrap() {

        loader = new ConfigLoader(); // you can add you own gson instance

        // loading
        Config config = loader.load(Config.class, new Config()); // pass the class and default options

        config.setName("John");

        // save
        loader.save(Config.class, config); // pass the class and the config object
    }
    
}
```

### Config Class
```java
@ConfigurationFile(
    directory = "./configs/test",
    file = "config.json"
)
class Config {
    private String name;
    @ConfigurationExclusion
    private int age;
}
```
