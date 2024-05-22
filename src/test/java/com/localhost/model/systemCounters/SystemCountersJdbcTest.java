package com.localhost.model.systemCounters;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SystemCountersJdbcTest {
//    private Properties properties = new Properties();
    private DBCPDataSourceFactory dataSource;
    private Connection connection;

    ISystemCounters counters;
    String counterType = "type";

    @BeforeEach
    public void setUp() {
//        try {
//            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        IModel model = new TestModelJdbc();
        counters = model.getSystemCounters();
        connection = model.getDataSource().getConnection();
    }

    @Test
    public void getCounterListEmptyTest() {
//        String counter1 = "";
//        String counter2 = "";
        int expectedNumber = 0;
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getCounterListTest() {
        String counter1 = "INSERT INTO counter.system_counters VALUES ('counter1')";
        String counter2 = "INSERT INTO counter.system_counters VALUES ('counter2')";
        try (Statement stmt = connection.createStatement()) {
//            Statement stmt = connection.createStatement();
            stmt.executeUpdate(counter1);
            stmt.executeUpdate(counter2);
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
        }
        int expectedNumber = 2;
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addCounterTrueTest() {
        boolean actual = counters.addCounter(counterType);
        Assertions.assertTrue(actual);
    }

//    @Test
//    public void addCounterFalseTest() {
//        counters.addCounter(counterType);
////        boolean actual = counters.addCounter(counterType);
//        String expectedMessage = "ERROR: duplicate key value violates unique constraint \"counter_type\"\n" +
//  "Подробности: Key (counter_type)=(type) already exists.";
////        String actualMessage =
//        SQLException thrown = Assertions.assertThrows(SQLException.class, () -> {
//            counters.addCounter(counterType);
//        }, "SQLException was expected");
////        String actualMessage = thrown.getMessage();
//        Assertions.assertEquals(expectedMessage, thrown.getMessage());
//    }

    @Test
    public void addCounterFalseTest() {
        counters.addCounter(counterType);
        boolean actual = counters.addCounter(counterType);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteCounterTest() {
        int expectedNumber = 0;
        counters.addCounter(counterType);
        counters.deleteCounter(counterType);
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

//    @AfterEach
//    public void clearDB() {
//        String events = "DELETE FROM counter.events";
//        String records = "DELETE FROM counter.records";
//        String systemCounters = "DELETE FROM counter.system_counters";
//        String userCounters = "DELETE FROM counter.user_counters";
//        String users = "DELETE FROM counter.users";
//        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
//            Statement stmt = connection.createStatement();
//            stmt.executeUpdate(events);
//            stmt.executeUpdate(records);
//            stmt.executeUpdate(systemCounters);
//            stmt.executeUpdate(userCounters);
//            stmt.executeUpdate(users);
//        } catch (SQLException e) {
//            System.err.println("SQL error code - " + e.getErrorCode());
//            System.err.println(e.getMessage());
//        }
//    }
}
