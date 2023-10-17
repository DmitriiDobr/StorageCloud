package com.example.storagecloud.Config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.application.db.url}")
    private String dbUrl;

    @Value("${spring.application.db.username}")
    private String username;

    @Value("${spring.application.db.password}")
    private String password;

    @Value("${spring.application.db.driver}")
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
