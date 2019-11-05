package com.qmtec.servicecore.model.dto.hive;


import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TablePartitionInfoDto {

    private Long authorityId;

    private String authorityName;

    private String menuUrl;

    private String createTime;

    private String checked;

    private Long parentId;



}
