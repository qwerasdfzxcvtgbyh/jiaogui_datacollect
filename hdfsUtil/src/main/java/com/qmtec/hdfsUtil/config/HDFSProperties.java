package com.qmtec.hdfsUtil.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("qmtec.hdfs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HDFSProperties {

    private String url;

    private String user;


}
