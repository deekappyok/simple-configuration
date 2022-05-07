package eu.dkcode.configuration.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class ConfigurationFile
 **/

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ConfigurationFile {

    String directory() default "./";
    String name();

}
