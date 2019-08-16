package com.qmtec.agent.comm;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 获取自定义配置常量类
 *
 */

@Component
public class Constant {

    /* @Getter
     @Value("${serviceCore.host}")
     public String serviceCoreHost; //serviceCore-Host端的Ip

     /*@Getter
     @Value("${flume.portBegin}")
     public int portBegin; //端口的开始(包括)

     @Getter
     @Value("${flume.portEnd}")
     public int portEnd; //端口的结束(不包括)

     @Getter
     @Value("${port.acquisition.times}")
     public int port_acquisition_times;//flume监控端口获取次数*/

}
