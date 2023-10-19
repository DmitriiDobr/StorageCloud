package com.example.storagecloud.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;


    @Bean
    public DataSource getDataSource(){
        DataSource dataSource = null;
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url(dbUrl);
        builder.username(username);
        builder.password(password);
        builder.driverClassName(driver);
        dataSource = builder.build();
        return dataSource;

    }
}
