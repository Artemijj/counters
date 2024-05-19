package com.localhost.model.dbcp;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DBCPDataSourceFactoryTest {
    private Properties properties = new Properties();
    private DBCPDataSourceFactory dataSource;
    Connection connection;

    @Test
    public void setUp() {
        try {
            properties.load(new FileInputStream("./src/main/resources/file.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource = new DBCPDataSourceFactory(properties.getProperty("liqProp"));
        connection = dataSource.getConnection();
        Assertions.assertTrue(connection instanceof Connection);
    }
}
