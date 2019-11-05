package com.qmtec.common.entity.schema;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Source {


    @Getter
    @Setter
    private int id;
    @Getter
    private String sourceName;
    @Getter
    @Setter
    private String sourceAlias;
    @Getter
    @Setter
    private String sourceType;
    @Getter
    @Setter
    private String sourceDesc;
    @Getter
    @Setter
    private String config;
}
