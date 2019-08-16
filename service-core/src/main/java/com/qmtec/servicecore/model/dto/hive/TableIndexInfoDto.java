package com.qmtec.servicecore.model.dto.hive;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableIndexInfoDto {

    private Long indexID;
    private String indexName;
    private Date  createTime;
    private String handlerClass;
    private String fieldName;
    private String fieldType;


}
