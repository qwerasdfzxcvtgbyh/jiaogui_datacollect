package com.qmtec.servicecore;

import com.qmtec.servicecore.listener.ApplicationEventListener;
import net.sf.oval.Validator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Import({
        Validator.class,
})
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
