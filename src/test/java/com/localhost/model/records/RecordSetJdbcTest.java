package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.Record;
import com.localhost.model.User;
import com.localhost.model.users.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class RecordSetJdbcTest {
    private IRecordSet recordSet;
    private String counterType = "type";
    private CounterValue counterValue = new CounterValue(new Date(), 1);
    IUsers users = new UsersJdbc();
    private User user = new User("newUser", "pass", false);

    private Record record = new Record(1, "newUser", counterType, counterValue);

    @BeforeEach
    public void setUp() {
        recordSet = new RecordSetJdbc();
    }

    @Test
    public void getRecordSetListTest() {
        int expectedNumber = 0;
        int actualNumber = recordSet.getRecordSetList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addRecordTrueTest() {
        users.addUser(user);
        boolean actual = recordSet.addRecord(record);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addRecordFalseTest() {
        users.addUser(user);
        recordSet.addRecord(record);
        boolean actual = recordSet.addRecord(record);
        Assertions.assertFalse(actual);
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
