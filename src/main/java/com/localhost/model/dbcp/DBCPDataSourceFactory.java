package com.localhost.model.dbcp;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPDataSourceFactory implements IDbcp {
    private BasicDataSource dataSource;

    String fileName;

    public DBCPDataSourceFactory(String fileProp) {
        fileName = fileProp;
        System.out.println("DBCPDataSourceFactory " + fileProp);//!!!!!!!!!!!!!!
    }

    private BasicDataSource getDataSource() {
        BasicDataSource localDataSource = dataSource;
        Properties properties = new Properties();
        try {
//            properties.load(new FileInputStream("./src/main/resources/db.properties"));
//            properties.load(new FileInputStream("./src/main/resources/db/changelog/liquibase.properties"));
            properties.load(new FileInputStream(fileName));
        } catch (IOException e) {
            System.err.println("File liquibase.properties does not exist...");
        }
        if (localDataSource == null) {
            synchronized (BasicDataSource.class) {
                localDataSource = dataSource;
                if (localDataSource == null) {
                    dataSource = localDataSource = new BasicDataSource();
//                    dataSource.setUrl(properties.getProperty("db.url"));
                    dataSource.setUrl(properties.getProperty("url"));
                    System.out.println("DBCPDataSourceFactory url " + properties.getProperty("url"));//!!!!!!!!!!!!!!
//                    dataSource.setUsername(properties.getProperty("db.username"));
                    dataSource.setUsername(properties.getProperty("username"));
                    System.out.println("DBCPDataSourceFactory username " + properties.getProperty("username"));//!!!!!!!!!!!!!!
//                    dataSource.setPassword(properties.getProperty("db.password"));
                    dataSource.setPassword(properties.getProperty("password"));
                    System.out.println("DBCPDataSourceFactory password " + properties.getProperty("password"));//!!!!!!!!!!!!!!
                }
            }
        }
        System.out.println("DBCPDataSourceFactory dataSource " + dataSource);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        return dataSource;
    }

    @Override
    public Connection getConnection() {
        try {
            System.out.println("DBCPDataSourceFactory getConnection " + getDataSource().getConnection());//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            return getDataSource().getConnection();
        } catch (SQLException e) {
            System.out.println("Здесь ошибка");//!!!!!!!!!!!!!!!!!
            System.err.println(e.getMessage());
//            throw new RuntimeException(e);
        }
        return null;
    }
}
