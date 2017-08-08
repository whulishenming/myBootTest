package com.lsm.boot.shiro.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ShiroDataSourceConfig {

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Value("${datasource.jpa.url}")
    private String url;

    @Bean
    public DataSource shiroDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(15);
        dataSource.setRemoveAbandonedTimeout(280);
        dataSource.setRemoveAbandoned(true);
        dataSource.setMaxWait(30000);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setTestOnBorrow(true);
        return dataSource;
    }
}
