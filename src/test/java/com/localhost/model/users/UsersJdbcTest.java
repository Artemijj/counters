package com.localhost.model.users;

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

@Testcontainers
public class UsersJdbcTest {
    @Container
        public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IUsers users;
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();

    private User user1 = new User("name1", "pass1", false);
    private User user2 = new User("name2", "pass2", false);

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        users = model.getUsers();
    }

    @Test
    public void getUserListTest() {
        int expectedNumber = 1;
        users.addUser(user1);
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserTrueTest() {
        boolean actual = users.addUser(user1);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addUserFalseTest() {
        users.addUser(user1);
        boolean actual = users.addUser(user1);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteUserTest() {
        users.addUser(user1);
        users.addUser(user2);
        users.deleteUser(user1);
        int expectedNumber = 1;
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserTest() {
        users.addUser(user1);
        User actualUser = users.getUser("name1");
        Assertions.assertInstanceOf(User.class, actualUser);
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
