package com.epam.config;

import org.aeonbits.owner.Converter;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.aeonbits.owner.Config.Key;

public class SystemPropertiesConvertor implements Converter<String> {

    @Override
    public String convert(Method method, String input) {
        Key keyAnnotation = method.getAnnotation(Key.class);
        String property = keyAnnotation == null ? method.getName() : keyAnnotation.value();

        return Optional.ofNullable(System.getProperty(property)).orElse(input);
    }
}