package com.song.cloudstorage.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * mainly for flexible configuration
 */
public class FileStorage {

    /**
     * get the path of the root directory for uploading
     * @return
     */
    public static String getFilePath() {
        return getProperties().getProperty("file_path");
    }

    /**
     * get the path of the directory for temporary portraits
     * @return
     */
    public static String getTempPortraitPath() {
        return getProperties().getProperty("temp_portrait_path");
    }

    /**
     * get the path of the directory for portraits
     */
    public static String getPortraitPath() {
        return getProperties().getProperty("portrait_path");
    }

    /**
     * read the storage.properties configuration file
     * @return
     */
    public static Properties getProperties() {
        InputStream input = FileStorage.class.getClassLoader().getResourceAsStream("storage.properties");
        Properties prop = new Properties();
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
