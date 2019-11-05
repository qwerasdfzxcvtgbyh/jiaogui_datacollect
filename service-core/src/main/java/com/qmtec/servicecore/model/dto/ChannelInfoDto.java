package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ChannelInfoDto {


    private String id;

    private String componentName;
    private Date startTime;
    private String ipAddr;
    private String putSuccessCount;
    private String putAttemptCount;
    private String percentage;
    private String channelSize;
    private String takeSuccessCount;
    private String takeAttemptCount;
    private Integer runState;
    private String runStateName;
    private String channelCapacity;
    private Date createTime;

    private Integer deleteFlag;
    private Date modifyTime;
}
