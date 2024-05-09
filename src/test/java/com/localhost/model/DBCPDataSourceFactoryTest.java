package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DBCPDataSourceFactoryTest {
    Connection connection;

    @Test
    public void setUp() {
        connection = DBCPDataSourceFactory.getConnection();
        Assertions.assertTrue(connection instanceof Connection);
    }
}
