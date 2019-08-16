package com.qmtec.common.util;

public class Systems {

    public Systems() {}

    public static String getSystemType() {
        String os = System.getProperty("os.name");
        return os.startsWith("Windows") ? "windows" : "linux";
    }

}
