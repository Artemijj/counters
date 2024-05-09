package com.localhost.model.users;

import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersJdbcTest {
    private IUsers users;

    private User user = new User("name", "pass", false);

    @BeforeEach
    public void setUp() {
        users = new UsersJdbc();
    }

    @Test
    public void getUserListTest() {
        int expectedNumber = 0;
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addUserTrueTest() {
        boolean actual = users.addUser(user);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addUserFalseTest() {
        users.addUser(user);
        boolean actual = users.addUser(user);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteUserTest() {
        users.addUser(user);
        users.deleteUser(user);
        int expectedNumber = 0;
        int actualNumber = users.getUserList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserTest() {
        users.addUser(user);
        User actualUser = users.getUser("name");
        Assertions.assertInstanceOf(User.class, actualUser);
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
