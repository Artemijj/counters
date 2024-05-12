package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.CounterValue;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.model.Record;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.Date;

public class ViewCountersByMonthActionTest {
    private IUserSession userSession = new UserSession();

    private ViewCountersByMonthAction viewCountersByMonthAction;

    @BeforeEach
    public void setUp() {
        viewCountersByMonthAction = new ViewCountersByMonthAction();
    }

    @Test
    public void viewCountersByMonthTest() {
        String one = "one";
        String two = "two";
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().createCounter(one);
            userSession.getAdminSession().createCounter(two);
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date();
        userSession.getModelRecordSet().addRecord(new Record("name", "one", new CounterValue(date, 123)));
        userSession.getModelRecordSet().addRecord(new Record("name", "two", new CounterValue(date, 321)));
        String counterNumber = "0";
        String year = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("0", year, month);
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewCountersByMonthAction.class, actual);
    }

    @Test
    public void viewCountersByMonthWrongCounterTest() {
        String one = "one";
        String two = "two";
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().createCounter(one);
            userSession.getAdminSession().createCounter(two);
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date();
        userSession.getModelRecordSet().addRecord(new Record("name", "one", new CounterValue(date, 123)));
        userSession.getModelRecordSet().addRecord(new Record("name", "two", new CounterValue(date, 321)));
        String counterNumber = "100";
        TestInputOutput tio = new TestInputOutput(counterNumber);
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewCountersByMonthAction.class, actual);
    }

    @Test
    public void userPageTest() {
        try {
            userSession.getAdminSession().createCounter("one");
            userSession.getAdminSession().createCounter("two");
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void wrongCounterMessageTest() {
        String one = "one";
        String two = "two";
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().createCounter(one);
            userSession.getAdminSession().createCounter(two);
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date();
        userSession.getModelRecordSet().addRecord(new Record("name", "one", new CounterValue(date, 123)));
        userSession.getModelRecordSet().addRecord(new Record("name", "two", new CounterValue(date, 321)));
        String counterNumber = "100";
        TestInputOutput tio = new TestInputOutput(counterNumber);
        viewCountersByMonthAction.execute(userSession, tio);
        String expected = "Введите корректный номер счётчика.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
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
