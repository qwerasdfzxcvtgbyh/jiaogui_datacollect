package com.qmtec.common.entity.flume;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FlumeMonitor {

    private Long contextId;

    private String flumeMonitorContent;

    private Boolean isRun;


}
