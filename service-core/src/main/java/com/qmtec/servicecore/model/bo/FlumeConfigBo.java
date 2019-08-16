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
public class FlumeConfigBo implements Cloneable, Serializable  {

    private static final long serialVersionUID = 1166837958857735545L;

    @NotNull(message = "任务contextId不能为空")
    private Long contextId;

    private String name;

    private String code;

    private String flumeHome;

    private String profileName;

    private String agentName;

    private String serverIp;

    private String serverPort;

    private String fileConfigContent;

    private String remark;

    private Integer runstate;

    private Integer monitorPort;

    private Integer processPid;

    private Date createTime;

    private Date modifyTime;

    private Date startupTime;

}
