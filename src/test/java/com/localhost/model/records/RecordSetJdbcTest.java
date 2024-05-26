package com.localhost.model.records;

import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.User;
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
public class RecordSetJdbcTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IRecordSet recordSet;
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    private String counterType = "type";
    private CounterValue counterValue = new CounterValue(new Date(), 1);
    private User user = new User("newUser", "pass", false);

    private Record record = new Record("newUser", counterType, counterValue);

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        recordSet = model.getRecordSet();
        model.getUsers().addUser(user);
        model.getSystemCounters().addCounter("type");
    }

    @Test
    public void getRecordSetListTest() {
        recordSet.addRecord(record);
        int expectedNumber = recordSet.getRecordSetList().size();
        recordSet.addRecord(record);
        int actualNumber = recordSet.getRecordSetList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addRecordTrueTest() {
        boolean actual = recordSet.addRecord(record);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addRecordFalseTest() {
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
