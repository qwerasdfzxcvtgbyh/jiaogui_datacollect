package com.qmtec.servicecore.model.dto.hive;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TbColumnsDto {

    private Long cdId;

    private String columnName;

    private String comment;

    private Integer integerIdx;

    private String typeName;

}
