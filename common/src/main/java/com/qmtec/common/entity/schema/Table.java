package com.qmtec.common.entity.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
public class Table {



    @Getter
    private int id;
    @Getter
    private String name;
    @Getter
    private String alias;

    @Getter
    private int sourceId;

    @Getter
    private String config;



}
