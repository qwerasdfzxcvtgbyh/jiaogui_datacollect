package com.qmtec.common.entity.schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class Column {
    @Setter
    @Getter
    private int id;

    @Setter
    @Getter
    private String name;

    @Setter
    @Getter
    private String alias;

    @Setter
    @Getter
    private String type;

    @Setter
    @Getter
    private int tableId;
}
