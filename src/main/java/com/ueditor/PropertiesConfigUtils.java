package com.ueditor;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenwenning on 2016/7/27.
 */
public class PropertiesConfigUtils {

   private static Properties properties = new Properties();

    static{

        try {
            properties.load(PropertiesConfigUtils.class.getClassLoader().getResourceAsStream("upload.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String key) {
        return properties.getProperty(key, "");
    }

}
