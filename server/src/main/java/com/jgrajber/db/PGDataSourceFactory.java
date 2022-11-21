package com.jgrajber.db;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PGDataSourceFactory {

    public static DataSource createPSQLDataSource() {

        PGSimpleDataSource dataSource = null;

        try (var input = new FileInputStream("src/main/resources/config.properties")) {

            Properties properties = new Properties();
            dataSource = new PGSimpleDataSource();

            properties.load(input);

            dataSource.setURL(properties.getProperty("pgsql.url"));
            dataSource.setUser(properties.getProperty("pgsql.username"));
            dataSource.setPassword(properties.getProperty("pgsql.password"));

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return dataSource;
    }
}
