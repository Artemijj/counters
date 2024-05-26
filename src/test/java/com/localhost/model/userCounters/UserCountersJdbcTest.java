package com.localhost.model.userCounters;

import com.localhost.model.User;
import com.localhost.model.UserCounter;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

@Testcontainers
public class UserCountersJdbcTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IUserCounters userCountersJdbc;
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    private User user = new User("name", "pass", false);
    private String counterName1 = "counterName1";
    private String counterName2 = "counterName2";

    private UserCounter userCounter1 = new UserCounter("name", counterName1);
    private UserCounter userCounter2 = new UserCounter("name", counterName2);

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        userCountersJdbc = model.getUserCounters();
        model.getUsers().addUser(user);
        model.getSystemCounters().addCounter(counterName1);
        model.getSystemCounters().addCounter(counterName2);
    }

    @Test
    public void getUserCountersListByUserTest() {
        int expectedNumber = 1;
        userCountersJdbc.addUserCounter(userCounter1);
        int actualNumber = userCountersJdbc.getUserCountersListByUser("name").size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserCounterTest() {
        int expectedNumber = 1;
        userCountersJdbc.addUserCounter(userCounter1);
        int actualNumber = userCountersJdbc.getUserCountersList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void deleteUserCounterTest() {
        int expectedNumber = 1;
        userCountersJdbc.addUserCounter(userCounter1);
        userCountersJdbc.addUserCounter(userCounter2);
        userCountersJdbc.deleteUserCounter(userCounter1);
        int actualNumber = userCountersJdbc.getUserCountersList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @AfterEach
    public void clearDB() {
        String events = "DELETE FROM counter.events";
        String records = "DELETE FROM counter.records";
        String systemCounters = "DELETE FROM counter.system_counters";
        String userCounters = "DELETE FROM counter.user_counters";
        String users = "DELETE FROM counter.users";
        try (Statement stmt = connection.createStatement()) {
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
