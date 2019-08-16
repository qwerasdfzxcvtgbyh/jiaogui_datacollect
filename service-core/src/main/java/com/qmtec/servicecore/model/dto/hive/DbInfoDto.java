package com.qmtec.servicecore.model.dto.hive;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DbInfoDto {

    private Long dbId;

    private String name;

    private String ownerName;

    private String ownerType;

    private String dbLocationUri;

    private String ctlgName;

    private int tabCount;

    private String desc;

}
