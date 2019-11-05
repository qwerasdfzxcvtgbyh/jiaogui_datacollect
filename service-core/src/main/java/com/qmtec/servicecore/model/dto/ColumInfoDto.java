package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ColumInfoDto implements Cloneable, Serializable {

    private String id;

    private String name;

    private String type;

}
