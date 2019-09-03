package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SinkInfoDto {

    private String id;

    private String componentName;
    private String ipAddr;
    private Date startTime;

    private String connectionCreatedCount;
    private String batchCompleteCount;
    private String batchEmptyCount;
    private String eventDrainattemptCount;
    private String batchUnderflowCount;
    private String connectionFailedCount;
    private String connectionClosedCount;
    private String rollbackCount;
    private String eventDrainSuccessCount;
    private String kafkaEventSendtimer;
    private Date createTime;
    private Integer runState;
    private String runStateName;

    private Integer deleteFlag;
    private Date modifyTime;

    private String eventWriteFail;
    private String channelReadFail;
}
