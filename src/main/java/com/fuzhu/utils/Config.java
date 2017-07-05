package com.fuzhu.utils;

/**
 * Created by asus on 2017/6/27.
 */

import java.util.Properties;


public class Config
{
    public static Properties instance;
    private static long lastFlushTime = 0l;

    public final static String PROTAL_CONFIG_FILE = "/config.properties";

    public static Properties getInstance() throws Exception{
        if(null == instance || instance.size() == 0){
            init();
        }
        return instance;
    }

    public static void init() throws Exception{
        instance = ConfigReader.readConfigFile(PROTAL_CONFIG_FILE);
    }

    public static String getPropertyValue(String key) throws Exception{
        flush();
        return getInstance().getProperty(key);
    }
    public static String getConfigValue(String key) throws Exception{
        return getPropertyValue(key);
    }
    public static String getConfigValue_(String key){
        try {
            return getPropertyValue(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void flush()
    {
        if ((System.currentTimeMillis()-lastFlushTime)>1000*60*10)
        {
            try {
                init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
