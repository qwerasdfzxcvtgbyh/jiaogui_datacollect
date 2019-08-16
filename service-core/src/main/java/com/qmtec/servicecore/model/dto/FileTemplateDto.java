package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileTemplateDto implements Cloneable, Serializable  {

    private static final long serialVersionUID = 1166837958857735545L;

    private String  id;

    private String name;

    private String code;

    private String fileConfigContent;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private Integer filetype;

    private String filetypename;



}
