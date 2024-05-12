package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChoiceViewedCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ChoiceViewedCountersAction choiceViewedCountersAction;

    @BeforeEach
    public void setUp() {
        choiceViewedCountersAction = new ChoiceViewedCountersAction();
    }

    @Test
    public void choiceViewedCountersTest() {
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
        TestInputOutput tio = new TestInputOutput("0");
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceViewedCountersAction.class, actual);
    }

    @Test
    public void choiceViewedWrongCountersTest() {
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
        TestInputOutput tio = new TestInputOutput("3");
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceViewedCountersAction.class, actual);
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
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void wrongMessageTest() {
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
        TestInputOutput tio = new TestInputOutput("3");
        choiceViewedCountersAction.execute(userSession, tio);
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
