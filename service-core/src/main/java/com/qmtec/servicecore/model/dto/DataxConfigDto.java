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

    private Date createTime;
    private Date modifyTime;
    private Date startupTime;

    private String jsonFileName;
    private String pythonscriptFileName;

    private String serverIp;
    private String serverPort;

    private Integer runstate;
    private String runstateName;

    private String remark;

    private String dataxHome;

    private String jsonFileContent;
    private String pythonscriptContent;

    private Boolean pythonSwitch;
    private Boolean jsonSwitch;

}
