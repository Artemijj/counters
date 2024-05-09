package com.localhost.model.systemCounters;

import com.localhost.model.DBCPDataSourceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SystemCountersJdbcTest {

    ISystemCounters counters;
    String counterType = "type";

    @BeforeEach
    public void setUp() {
        counters = new SystemCountersJdbc();
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
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
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

    @AfterEach
    public void clearDB() {
        String events = "DELETE FROM counter.events";
        String records = "DELETE FROM counter.records";
        String systemCounters = "DELETE FROM counter.system_counters";
        String userCounters = "DELETE FROM counter.user_counters";
        String users = "DELETE FROM counter.users";
        try (Connection connection = DBCPDataSourceFactory.getConnection()) {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(events);
            stmt.executeUpdate(records);
            stmt.executeUpdate(systemCounters);
            stmt.executeUpdate(userCounters);
            stmt.executeUpdate(users);
        } catch (SQLException e) {
            System.err.println("SQL error code - " + e.getErrorCode());
            System.err.println(e.getMessage());
        }
    }
}
