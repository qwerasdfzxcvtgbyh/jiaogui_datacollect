package com.qmtec.hdfsUtil.config;


import com.qmtec.hdfsUtil.resource.hdfs.HdfsUtil;

public class HdfsUtolGenerator {
    public static HdfsUtil generator(HDFSProperties properties) {
        HdfsUtil.setNameNodeUrl(properties.getUrl());
        HdfsUtil.setUser(properties.getUser());
        return new HdfsUtil();
    }
}
