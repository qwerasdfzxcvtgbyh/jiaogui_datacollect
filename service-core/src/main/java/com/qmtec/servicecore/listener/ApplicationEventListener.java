package com.qmtec.servicecore.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;

@Slf4j
public class ApplicationEventListener implements ApplicationListener {

    @Autowired
    public FlumeMonitorListener flumeMonitorListener;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        // 在这里可以监听到Spring Boot的生命周期
        if (event instanceof ContextRefreshedEvent) {
            // 应用刷新
            log.debug("应用刷新");
        }else if (event instanceof ApplicationReadyEvent) {
            // 应用已启动完成
            log.debug("应用已启动完成");

        } else if (event instanceof ContextClosedEvent) {
            // 应用关闭
            flumeMonitorListener.destroy();
            log.debug("应用关闭");
        } else if (event instanceof ApplicationFailedEvent){
            // 应用关闭
            log.debug("应用失败");
        }

        /**
         *
         * #context.listener.classes=com.qmtec.servicecore.listener.ApplicationEventListener
         *
         * if (event instanceof ApplicationEnvironmentPreparedEvent) {
         // 初始化环境变量
         log.debug("初始化环境变量");
         }else if (event instanceof ApplicationPreparedEvent) {
         // 初始化完成
         log.debug("初始化完成");
         } else if (event instanceof ContextStartedEvent) {
         // 应用启动，需要在代码动态添加监听器才可捕获
         log.debug("应用启动");
         } else if (event instanceof ContextStoppedEvent) {
         // 应用停止
         log.debug("应用停止");
         }*/
    }
}