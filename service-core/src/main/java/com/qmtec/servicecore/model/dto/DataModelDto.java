package com.qmtec.servicecore.model.dto;


import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DataModelDto implements Cloneable, Serializable {

    private String id;

    private String name;

    private String code;

    private Date createTime;

    private Date modifyTime;

    private String remark;

    private List<ColumInfoDto> columInfoDtoList;
}
