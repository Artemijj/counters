package com.localhost.model.userCounters;

import com.localhost.model.User;
import com.localhost.model.UserCounter;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import org.junit.jupiter.api.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserCountersJdbcTest {
//    private Properties properties = new Properties();
    private IUserCounters userCountersJdbc;
    private User user = new User("name", "pass", false);
    private String counterName = "counterName";

    private UserCounter userCounter;

    @BeforeEach
    public void setUp() {
//        try {
//            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        IModel model = new TestModelJdbc();
        userCountersJdbc = model.getUserCounters();
        userCounter = new UserCounter("name", counterName);
    }

    @Test
    public void getUserCountersListByUserTest() {
        int expectedNumber = 1;
        userCountersJdbc.addUserCounter(userCounter);
        int actualNumber = userCountersJdbc.getUserCountersListByUser("name").size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserCounterTest() {
        int expectedNumber = 1;
        userCountersJdbc.addUserCounter(userCounter);
        int actualNumber = userCountersJdbc.getUserCountersList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void deleteUserCounterTest() {
        int expectedNumber = 0;
        userCountersJdbc.addUserCounter(userCounter);
        userCountersJdbc.deleteUserCounter(userCounter);
        int actualNumber = userCountersJdbc.getUserCountersList().size();
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
