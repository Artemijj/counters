package com.localhost.model;

import com.localhost.model.DBCPDataSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.time.Duration;
import java.util.Properties;

@Testcontainers
public class DBCPDataSourceFactoryTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private Properties properties = new Properties();
    private DBCPDataSourceFactory dataSource;
    Connection connection;

    @Test
    public void getConnectionTest() {
        try {
            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        dataSource = new DBCPDataSourceFactory(properties.getProperty("appPropTest"));
        connection = dataSource.getConnection();
        Assertions.assertTrue(connection instanceof Connection);
    }
}
