package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DataxConfigDto {

    private String id;

    private String name;
    private String code;

    private String jsonFilePath;
    private String jsonFileName;
    private String jsonFileContent;

    private String pythonscriptPath;
    private String pythonscriptFileName;
    private String pythonscriptContent;

    private String serverIp;
    private String serverPort;

    private String remark;

    private Integer runstate;

    private Date createTime;
    private Date modifyTime;
    private Date startupTime;

}
