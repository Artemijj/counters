package com.localhost.in;

import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.User;
import com.localhost.model.systemCounters.ISystemCounters;
import com.localhost.model.events.IEventLog;
import com.localhost.model.records.IRecordSet;
import com.localhost.model.users.IUsers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        userSession.getModelUsers().addUser(user);
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
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        boolean actual = userSession.isAdmin();
        Assertions.assertFalse(actual);
    }

    @Test
    public void isUserTrueTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        boolean actual = userSession.isUser();
        Assertions.assertTrue(actual);
    }

    @Test
    public void getLoginTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        String expectedLogin = "name";
        String actualLogin = userSession.getLogin();
        Assertions.assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void getLastValueClassTest() {
        CounterType counterType = new CounterType("type");
        String counterTypeName = counterType.getCounterTypeName();
        CounterValue counterValue = new CounterValue(new Date(), 1);
        Record record = new Record(1, "name", counterTypeName, counterValue);
        userSession.getModelRecordSet().addRecord(record);
        CounterValue actual = userSession.getLastValue(counterType);
        Assertions.assertInstanceOf(CounterValue.class, actual);
    }

    @Test
    public void getLastValueValueTest() {
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        CounterType counterType = new CounterType("type");
        String counterTypeName = counterType.getCounterTypeName();
        CounterValue counterValue = new CounterValue(new Date(), 1);
        Record record = new Record(1, "name", counterTypeName, counterValue);
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
        int actualNumber = userSession.getModelEventLog().getEventLog().size();
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
        userSession.getModelUsers().addUser(user);
        userSession.logIn("name", "pass");
        CounterType one = new CounterType("one");
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
        CounterType one = new CounterType("one");
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
}
