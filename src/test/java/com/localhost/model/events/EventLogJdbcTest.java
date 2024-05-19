package com.localhost.model.events;

import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.model.Event;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

@Testcontainers
public class EventLogJdbcTest {
    private Properties properties = new Properties();
    @Container
    public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("src/test/resources/docker-compose-test.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IEventLog eventLog;

    Event event = new Event("login", new Date(), "activity");

    @BeforeEach
    public void setUp() {
        try {
            properties.load(new FileInputStream("./src/test/resources/file-test.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        eventLog = new EventLogJdbc(properties.getProperty("liqPropTest"));
    }

    @Test
    public void getEventLogTest() {
        int expectedNumber = 0;
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
