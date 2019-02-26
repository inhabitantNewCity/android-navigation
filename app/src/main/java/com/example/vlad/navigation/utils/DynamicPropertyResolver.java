package com.example.vlad.navigation.utils;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DynamicPropertyResolver {

    private static final String CONFIGURATION_FILE_NAME = "application/application.properties";

    public static String getStringProperty(String key, String defaultValue) {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DynamicPropertyResolver.class.getResourceAsStream(CONFIGURATION_FILE_NAME);
            properties.load(inputStream);
            String value = properties.getProperty(key);
            return value != null ? value : defaultValue;
        } catch (IOException e){
            Log.e("DynamicPropertyResolver", e.getMessage());
        }
        return defaultValue;

    }

    public static Boolean getBooleanProperty(String key, Boolean defaultValue){
        try {
            Properties properties = new Properties();
            InputStream inputStream = DynamicPropertyResolver.class.getResourceAsStream(CONFIGURATION_FILE_NAME);
            properties.load(inputStream);
            Boolean value = new Boolean(properties.getProperty(key));
            return value != null ? value : defaultValue;
        } catch (IOException e){
            Log.e("DynamicPropertyResolver", e.getMessage());
        }
        return  new Boolean(defaultValue);
    }
}
