package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.User;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import com.localhost.model.users.*;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class RecordSetJdbcTest {
//    private Properties properties = new Properties();
    private IRecordSet recordSet;
    private String counterType = "type";
    private CounterValue counterValue = new CounterValue(new Date(), 1);
    IUsers users;
    private User user = new User("newUser", "pass", false);

    private Record record = new Record("newUser", counterType, counterValue);

    @BeforeEach
    public void setUp() {
//        try {
//            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        IModel model = new TestModelJdbc();
        recordSet = model.getRecordSet();
        users = model.getUsers();
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
