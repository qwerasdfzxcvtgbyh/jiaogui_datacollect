package com.qmtec.agent;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.github.dadiyang.httpinvoker.annotation.HttpApiScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableApolloConfig
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.qmtec.agent"})
@HttpApiScan()
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }

}
