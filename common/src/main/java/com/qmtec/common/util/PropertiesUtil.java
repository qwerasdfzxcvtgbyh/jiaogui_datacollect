package com.qmtec.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

    public PropertiesUtil() {
    }

    public static Properties getProperties(Class clazz, String paramsFileName) {
        InputStream in = clazz.getClassLoader().getResourceAsStream(paramsFileName);
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException var5) {
            var5.printStackTrace();
        }
        return prop;
    }

}