package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ResourceDbDto implements Cloneable, Serializable {

    private String id;

    private String name;

    private Date createTime;

    private Date modifyTime;

    private Date startupTime;

    private String remark;

    private Integer databaseType;
    private String  databaseTypeName;

    private String define;

}
