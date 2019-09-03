package com.qmtec.hdfsUtil.config;

import com.qmtec.hdfsUtil.resource.hdfs.HdfsUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HDFSProperties.class)
public class HdfsAutoConfiguration {

    @Bean
    public HdfsUtil createHdfsUtil(HDFSProperties properties) {
        return HdfsUtolGenerator.generator(properties);
    }

}
