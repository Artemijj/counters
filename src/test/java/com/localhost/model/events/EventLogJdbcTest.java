package com.localhost.model.events;

import com.localhost.model.Event;
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
import java.util.Date;

@Testcontainers
public class EventLogJdbcTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IEventLog eventLog;
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();

    Event event = new Event("login", new Date(), "activity");

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        eventLog = model.getEventLog();
    }

    @Test
    public void getEventLogTest() {
        eventLog.addEvent(event);
        eventLog.addEvent(event);
        int expectedNumber = eventLog.getEventLogList().size();
        int actualNumber = eventLog.getEventLogList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addEventTrueTest() {
        boolean actual = eventLog.addEvent(event);
        Assertions.assertTrue(actual);
    }

//    @Test
//    public void addEventFalseTest() {
//        eventLog.addEvent(event);
//        boolean actual = eventLog.addEvent(event);
//        Assertions.assertFalse(actual);
//    }


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
