package com.jgrajber.config;

import com.jgrajber.db.PGDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.jgrajber")
public class AppConfig {

    @Bean
    public DataSource pgDataSource() {
        return PGDataSourceFactory.createPSQLDataSource();
    }
}
