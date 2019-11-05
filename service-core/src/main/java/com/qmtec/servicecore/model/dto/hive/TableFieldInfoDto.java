package com.qmtec.servicecore.model.dto.hive;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TableFieldInfoDto {

    private String field;
    private Long tableID;
    private Integer fieldID;
    private String type;
    private Boolean isPartitionFiled;
    private Boolean isIndexFiled;
    private String comment;

}
