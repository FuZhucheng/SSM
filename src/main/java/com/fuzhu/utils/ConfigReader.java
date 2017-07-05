package com.fuzhu.utils;

/**
 * Created by asus on 2017/6/27.
 */

import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public static Properties readConfigFile(String configFileName) throws Exception {
        if (StringUtils.isBlank(configFileName)) {
            throw new Exception("Config file [" + configFileName + "] is not exist or null!");
        }

        Properties p = null;
        try {
            InputStream in = ConfigReader.class.getResourceAsStream(configFileName);
            p = new Properties();
            p.load(in);
            in.close();
        } catch (Exception ex) {
            throw new Exception("Config file read error [" + configFileName + "]");
        }

        if (null == p || p.size() == 0) {
            throw new Exception("Config file is empty [" + configFileName + "]");
        }

        return p;
    }

}
