package com.qmtec.servicecore.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SourceInfoDto {

    private String id;
    private String componentName;
    private String ipAddr;
    private String currentThroughput;
    private String maxThroughput;
    private String eventCount;
    private String averageThroughput;

    private Integer runState;
    private String runStateName;

    private Integer deleteFlag;
    private Date createTime;
    private Date modifyTime;

    private String eventGetTimer;
    private String appendBatchAcceptedCount;
    private String eventAcceptedCount;
    private String appendReceivedCount;
    private String appendBatchReceivedCount;
    private String kafkaCommitTimer;
    private String eventReceivedCount;
    private String appendAcceptedCount;
    private String openConnectionCount;
    private String kafkaEmptyCount;
}
