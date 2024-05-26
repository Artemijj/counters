package com.localhost.model.systemCounters;

import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import org.junit.jupiter.api.Assertions;
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
public class SystemCountersJdbcTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    ISystemCounters counters;
    String counterType1 = "type1";
    String counterType2 = "type2";

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        counters = model.getSystemCounters();
    }

    @Test
    public void getCounterListEmptyTest() {
        int expectedNumber = 0;
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getCounterListTest() {
        counters.addCounter(counterType1);
        int expectedNumber = 1;
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addCounterTrueTest() {
        boolean actual = counters.addCounter(counterType1);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addCounterFalseTest() {
        counters.addCounter(counterType1);
        boolean actual = counters.addCounter(counterType1);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteCounterTest() {
        int expectedNumber = 1;
        counters.addCounter(counterType1);
        counters.addCounter(counterType2);
        counters.deleteCounter(counterType1);
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
