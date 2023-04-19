package com.epam.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    private ConfigurationManager() {
        throw new IllegalStateException("It is not allowed to create object of this class");
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
