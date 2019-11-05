package com.qmtec.servicecore.model.bo;

import lombok.*;
import net.sf.oval.constraint.NotNull;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DataxConfigBo implements Cloneable, Serializable  {

    private static final long serialVersionUID = 3460103249777627803L;

    @NotNull(message = "任务id不能为空")
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
    private Integer processPid;

    private Integer runstate;
    private String remark;

    private String dataxHome;

    private String jsonFileContent;
    private String pythonscriptContent;

    private Boolean pythonSwitch;
    private Boolean jsonSwitch;
}
