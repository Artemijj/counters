package com.localhost.in;

import com.localhost.model.CounterValue;
import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.model.Record;
import com.localhost.model.User;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class UserSessionTest {
    private UserSession userSession;
    private User user = new User("name", "pass", false);

    @BeforeEach
    public void setUp() {
        userSession = new UserSession();
    }

    @Test
    public void logInTrueTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        boolean actual = userSession.logIn("name", "pass");
        Assertions.assertTrue(actual);
    }

    @Test
    public void logInFalseTest() {
        userSession.getModelUsers().addUser(user);
        boolean actual = userSession.logIn("name", "newPass");
        Assertions.assertFalse(actual);
    }

    @Test
    public void logOutTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        userSession.logOut();
        String expectedLogin = "";
        String actualLogin = userSession.getLogin();
        Assertions.assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void isAdminFalseTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        boolean actual = userSession.isAdmin();
        Assertions.assertFalse(actual);
    }

    @Test
    public void isUserTrueTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        boolean actual = userSession.isUser();
        Assertions.assertTrue(actual);
    }

    @Test
    public void getLoginTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String expectedLogin = "name";
        String actualLogin = userSession.getLogin();
        Assertions.assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void getLastValueClassTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String counterType = "type";
//        String counterTypeName = counterType.getCounterTypeName();
        CounterValue counterValue = new CounterValue(new Date(), 1);
        Record record = new Record("name", counterType, counterValue);
        userSession.getModelRecordSet().addRecord(record);
        CounterValue actual = userSession.getLastValue(counterType);
        Assertions.assertInstanceOf(CounterValue.class, actual);
    }

    @Test
    public void getLastValueValueTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String counterType = "type";
//        String counterTypeName = counterType.getCounterTypeName();
        CounterValue counterValue = new CounterValue(new Date(), 1);
        Record record = new Record("name", counterType, counterValue);
        userSession.getModelRecordSet().addRecord(record);
        int expectedValue = 1;
        CounterValue actualCounterValue = userSession.getLastValue(counterType);
        int actualValue = actualCounterValue.getValue();
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void getAdminSessionTest() {
        IAdminSession actualAdminSession = userSession.getAdminSession();
        Assertions.assertInstanceOf(IAdminSession.class, actualAdminSession);
    }

    @Test
    public void getModelUsersTest() {
        IUsers actualUsers = userSession.getModelUsers();
        Assertions.assertInstanceOf(IUsers.class, actualUsers);
    }

    @Test
    public void getModelCountersTest() {
        ISystemCounters actualCounters = userSession.getModelSystemCounters();
        Assertions.assertInstanceOf(ISystemCounters.class, actualCounters);
    }

    @Test
    public void getModelRecordSetTest() {
        IRecordSet actualRecordSet = userSession.getModelRecordSet();
        Assertions.assertInstanceOf(IRecordSet.class, actualRecordSet);
    }

    @Test
    public void getModelEventLogTest() {
        IEventLog actualEventLog = userSession.getModelEventLog();
        Assertions.assertInstanceOf(IEventLog.class, actualEventLog);
    }

    @Test
    public void addEventTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        userSession.addEvent("event");
        int expectedNumber = 1;
        int actualNumber = userSession.getModelEventLog().getEventLogList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void isUserExistTest() {
        userSession.getModelUsers().addUser(user);
        boolean actual = userSession.isUserExist("name");
        Assertions.assertTrue(actual);
    }

    @Test
    public void addCounterTest() {
        try {
            userSession.getAdminSession().addUser("name", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
//        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String one = "one";
        try {
            userSession.getAdminSession().createCounter("one");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.addCounter(one);
        int expectedNumber = 1;
        int actualNumber;
        try {
            actualNumber = userSession.getAdminSession().getUserCounters("name").length;
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void deleteCounterTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String one = "one";
        try {
            userSession.getAdminSession().createCounter(one);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.addCounter(one);
        userSession.deleteCounter(one);
        int expectedNumber = 0;
        int actualNumber;
        try {
            actualNumber = userSession.getAdminSession().getUserCounters("name").length;
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
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
