package com.localhost.in;

import com.localhost.model.*;
import com.localhost.model.Record;
import com.localhost.model.dbcp.DBCPDataSourceFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class AdminSessionTest {
    private IAdminSession adminSession;
    private IUserSession userSession = new UserSession();

    @BeforeEach
    public void setUp() {
        adminSession = new AdminSession(userSession);
    }

    @Test
    public void addUserTest() {
        try {
            adminSession.addUser("newUser", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("newUser", "passwd");
        boolean actual = userSession.isUser();
        Assertions.assertTrue(actual);
    }

    @Test
    public void addAdminTest() {
        try {
            adminSession.addAdmin("newAdmin", "passwd1");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("newAdmin", "passwd1");
        boolean actual = userSession.isAdmin();
        Assertions.assertTrue(actual);
    }

    @Test
    public void removeUserTest() {
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.removeUser("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        boolean actual = userSession.isUserExist("newUser");
        Assertions.assertFalse(actual);
    }

    @Test
    public void getAllUsersTest() {
        User[] users;
        try {
            adminSession.addUser("user1", "passwd1");
            adminSession.addUser("user2", "passwd2");
            users = adminSession.getAllUsers();
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 2;
        int actualNumber = users.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserDataTest() {
        User user;
        try {
            adminSession.addUser("newUser", "passwd");
            user = adminSession.getUserData("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertInstanceOf(User.class, user);
    }

    @Test
    public void setPasswordTest() {
        try {
            adminSession.addUser("newUser", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("newUser", "passwd");
        try {
            adminSession.setPassword("newUser", "newPasswd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        boolean actual = userSession.logIn("newUser", "newPasswd");
        Assertions.assertTrue(actual);
    }

    @Test
    public void setAddressTest() {
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.setAddress("newUser", "address");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        String expectedAddress = "address";
        String actualAddress = userSession.getModelUsers().getUser("newUser").getAddress();
        Assertions.assertEquals(expectedAddress, actualAddress);
    }

    @Test
    public void setPhoneTest() {
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.setPhone("newUser", "+7896321");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        String expectedPhone = "+7896321";
        String actualPhone = userSession.getModelUsers().getUser("newUser").getPhoneNumber();
        Assertions.assertEquals(expectedPhone, actualPhone);
    }

    @Test
    public void getAllSystemCountersTest() {
        String one = "one";
        String two = "two";
        String three = "three";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        userSession.getModelSystemCounters().addCounter(three);
        String[] systemCounters = adminSession.getAllSystemCounters();
        int expectedNumber = 3;
        int actualNumber = systemCounters.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserCountersTest() {
        String one = "one";
        String two = "two";
        String three = "three";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        userSession.getModelSystemCounters().addCounter(three);
        String[] userCounters;
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.linkCounter("newUser", one);
            adminSession.linkCounter("newUser", two);
            adminSession.linkCounter("newUser", three);
            userCounters = adminSession.getUserCounters("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 3;
        int actualNumber = userCounters.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void linkCounterTest() {
        String one = "one";
        userSession.getModelSystemCounters().addCounter(one);
        String[] userCounters;
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.linkCounter("newUser", one);
            userCounters = adminSession.getUserCounters("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 1;
        int actualNumber = userCounters.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void unlinkCounterTest() {
        String one = "one";
        userSession.getModelSystemCounters().addCounter(one);
        String[] userCounters;
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.linkCounter("newUser", one);
            adminSession.unlinkCounter("newUser", one);
            userCounters = adminSession.getUserCounters("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 0;
        int actualNumber = userCounters.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getCounterValuesTest() {
        String one = "one";
//        String oneTypeName = one.getCounterTypeName();
        userSession.getModelSystemCounters().addCounter(one);
//        User user;
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.linkCounter("newUser", one);
//            user = adminSession.getUserData("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("newUser", "passwd");
        CounterValue counterValue1 = new CounterValue(new Date(0L), 1);
        CounterValue counterValue2 = new CounterValue(new Date(), 2);
        userSession.getModelRecordSet().addRecord(new Record("newUser", one, counterValue1));
        userSession.getModelRecordSet().addRecord(new Record("newUser", one, counterValue2));
        CounterValue[] counterValues;
        try {
            counterValues = adminSession.getCounterValues("newUser", one);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 2;
        int actualNumber = counterValues.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getUserActivitiesTest() {
        try {
            adminSession.addUser("newUser", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.getModelEventLog().addEvent(new Event("newUser", new Date(), "event1"));
        userSession.getModelEventLog().addEvent(new Event("newUser", new Date(), "event2"));
        userSession.getModelEventLog().addEvent(new Event("newUser", new Date(), "event3"));
        Event[] events;
        try {
            events = adminSession.getUserActivities("newUser");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        int expectedNumber = 3;
        int actualNumber = events.length;
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void setFioTest() {
        try {
            adminSession.addUser("newUser", "passwd");
            adminSession.setFio("newUser", "fio");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        String expectedAFio = "fio";
        String actualFio = userSession.getModelUsers().getUser("newUser").getFio();
        Assertions.assertEquals(expectedAFio, actualFio);
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
