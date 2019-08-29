package com.qmtec.servicecore;

import com.qmtec.hdfsUtil.config.HdfsAutoConfiguration;
import com.qmtec.servicecore.listener.ApplicationEventListener;
import net.sf.oval.Validator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({
        Validator.class,
})
@ImportAutoConfiguration(value = HdfsAutoConfiguration.class)
@MapperScan("com.qmtec.servicecore.dao")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.qmtec.servicecore"})
public class ServiceCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCoreApplication.class, args);
    }

    @Bean
    public ApplicationEventListener appApplicationListener(){
        return new ApplicationEventListener();
    }

}
