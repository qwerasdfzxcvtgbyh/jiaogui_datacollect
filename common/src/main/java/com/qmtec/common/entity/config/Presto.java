package com.qmtec.common.entity.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Presto {
    private String url;
    private String userName;
    private String password;
}
