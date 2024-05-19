package com.localhost.model;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RecordTest {
    private Record record;
    @Mock
    User user;
    private String counterType = "type";
    @Mock
    CounterValue counterValue;

    @BeforeEach
    public void setUp() {
        record = new Record("newUser", counterType, counterValue);
    }

//    @Test
//    public void getIdTest() {
//        int expectedId = 1;
//        int actualId = record.getId();
//        Assertions.assertEquals(expectedId, actualId);
//    }

    @Test
    public void getLoginTest() {
        String expectedUser = "newUser";
        String actualUser = record.getLogin();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getCounterTypeTest() {
        String expectedCounterType = counterType;
        String actualCounterType = record.getCounterType();
        Assertions.assertEquals(expectedCounterType, actualCounterType);
    }

    @Test
    public void getCounterValueTest() {
        CounterValue expectedCounterValue = counterValue;
        CounterValue actualCounterValue = record.getCounterValue();
        Assertions.assertEquals(expectedCounterValue, actualCounterValue);
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
