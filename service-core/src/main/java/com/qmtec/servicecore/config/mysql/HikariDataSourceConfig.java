package com.qmtec.servicecore.config.mysql;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class HikariDataSourceConfig {

    @Bean("hikariDataSource")
    @ConfigurationProperties("datasource.local")
    public DataSource hikariDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * db2数据库配置
     */
    @Bean("hiveDataSource")
    @ConfigurationProperties(prefix = "datasource.hive")
    public DataSource hiveDataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 动态数据库配置
     */
    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(hikariDataSource());

        // 配置多数据源
        Map<Object, Object> dsMap = new HashMap(5);
        dsMap.put("hikariDataSource", hikariDataSource());
        dsMap.put("hiveDataSource", hiveDataSource());

        dynamicDataSource.setTargetDataSources(dsMap);

        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    /*@Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }*/

}
