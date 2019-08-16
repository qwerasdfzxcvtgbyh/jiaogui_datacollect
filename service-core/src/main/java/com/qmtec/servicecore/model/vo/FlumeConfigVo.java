package com.qmtec.servicecore.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import net.sf.oval.constraint.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
public class FlumeConfigVo {

    @NotNull(message="任务contextId不能为空")
    private Long contextId;

    private Integer monitorPort;

    private String serverIp;

    private Integer runstate;

    private Integer processPid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startupTime;

}
