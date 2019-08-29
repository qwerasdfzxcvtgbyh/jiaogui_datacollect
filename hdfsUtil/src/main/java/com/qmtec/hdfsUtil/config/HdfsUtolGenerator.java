package com.qmtec.hdfsUtil.config;

import com.qmtec.hdfsUtil.HDFSUtil;

public class HdfsUtolGenerator {
    public static HDFSUtil generator(HDFSProperties properties) {
        HDFSUtil.setNameNodeUrl(properties.getUrl());
        HDFSUtil.setUser(properties.getUser());
        return new HDFSUtil();
    }
}
