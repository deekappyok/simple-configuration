package eu.dkcode.configuration.adapters;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import eu.dkcode.configuration.annotations.ConfigurationExclusion;

/**
 * @Author Kacper 'DeeKaPPy' Horbacz
 * @Created 07.05.2022
 * @Class ExclusionAdapter
 **/

public class ExclusionAdapter implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getAnnotation(ConfigurationExclusion.class) != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
