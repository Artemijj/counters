package com.localhost.model;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSourceFactory {
    private BasicDataSource dataSource;

    String fileName;

    public DBCPDataSourceFactory(String fileProp) {
        fileName = fileProp;
    }

    private BasicDataSource getDataSource() {
        BasicDataSource localDataSource = dataSource;
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            System.err.println("File liquibase.properties does not exist...");
        }
        if (localDataSource == null) {
            synchronized (BasicDataSource.class) {
                localDataSource = dataSource;
                if (localDataSource == null) {
                    dataSource = localDataSource = new BasicDataSource();
                    dataSource.setUrl(properties.getProperty("url"));
                    dataSource.setUsername(properties.getProperty("username"));
                    dataSource.setPassword(properties.getProperty("password"));
                }
            }
        }
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
