package com.localhost.model;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSourceFactory {
    private static volatile BasicDataSource dataSource;
    private static BasicDataSource getDataSource() {
        BasicDataSource localDataSource = dataSource;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./src/main/resources/db.properties"));
        } catch (IOException e) {
            System.err.println("The file does not exist...");
        }
        if (localDataSource == null) {
            synchronized (BasicDataSource.class) {
                localDataSource = dataSource;
                if (localDataSource == null) {
                    dataSource = localDataSource = new BasicDataSource();
                    dataSource.setUrl(properties.getProperty("db.url"));
                    dataSource.setUsername(properties.getProperty("db.username"));
                    dataSource.setPassword(properties.getProperty("db.password"));
                }
            }
        }

        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
