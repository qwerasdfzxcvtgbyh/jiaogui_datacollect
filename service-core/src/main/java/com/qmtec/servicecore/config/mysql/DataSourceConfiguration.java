package com.qmtec.servicecore.config.mysql;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@Slf4j
public class DataSourceConfiguration {

    @Autowired
    private Environment env;

    @Value("${jdbc.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean(name="hikariDataSource")
    @ConfigurationProperties(prefix="datasource.local")
    @Primary
    public  DataSource hikariDataSource(){
        DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("========= MASTER :{} =======",masterDataSource);
        return masterDataSource;
    }

    @Bean(name="hiveDataSource")
    @ConfigurationProperties(prefix="datasource.hive")
    public DataSource hiveDataSource(){
        DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
        log.info("========= SLAVE :{} ========",slaveDataSource);
        return slaveDataSource;
    }

    /**
     * @Primary 给注解表示在同一个接口有多个实现类可以注入的时候，默认选择那个，而不是让@Autowied 注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实力的一个注入（列如有多个DataSource 的类型）
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("hikariDataSource") DataSource hikariDataSource,
                                        @Qualifier("hiveDataSource") DataSource hiveDataSource){
        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put("hikariDataSource", hikariDataSource);
        targetDataSources.put("hiveDataSource", hiveDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources); // 给方法是AbstractRoutingDataSource 的方法
        dataSource.setDefaultTargetDataSource(hikariDataSource); // 默认的datasource 设置为  masterDataSource
        return dataSource;
    }

}
