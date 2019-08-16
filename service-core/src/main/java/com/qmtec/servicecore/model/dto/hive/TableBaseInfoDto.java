package com.qmtec.servicecore.model.dto.hive;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableBaseInfoDto {

    private Long tableID;
    private String tableName;
    private String owner;
    private String createTime;
    private String location;
    private String inputFormat;
    private String dbName;
    private String tableType;
    private String hasPartition;
    private Long fieldsCount;
    private Boolean isCompress;
    private String compressFormat;
    private String lastUpdateTime;
    private String fieldDelimiter;
    private String comment;

}
