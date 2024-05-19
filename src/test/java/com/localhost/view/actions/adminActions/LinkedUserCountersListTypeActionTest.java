package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class LinkedUserCountersListTypeActionTest {
    private IUserSession userSession = new UserSession();

    private LinkedUserCountersTypeAction linkedUserCountersTypeAction;

    @BeforeEach
    public void setUp() {
        linkedUserCountersTypeAction = new LinkedUserCountersTypeAction();
    }

    @Test
    public void linkedUserCountersTypeTest() {
        TestInputOutput tio = new TestInputOutput("user", "null");
        IAction actual = linkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(LinkedUserCountersTypeAction.class, actual);
    }

    @Test
    public void adminPageTest() {
        String one = "one";
        String two = "two";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user", "0");
        IAction actual = linkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void linkedUserCountersTypeMessageTest() {
        String one = "one";
        String two = "two";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        String wrongNumber = Integer.MAX_VALUE + "";
        TestInputOutput tio = new TestInputOutput("user", wrongNumber);
        linkedUserCountersTypeAction.execute(userSession, tio);
        String expected = "Введите корректный номер счётчика.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
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
