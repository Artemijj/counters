package com.localhost.view.actions.adminActions;

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

public class ViewAllCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ViewAllCountersAction viewAllCountersAction;

    @BeforeEach
    public void setUp() {
        viewAllCountersAction = new  ViewAllCountersAction();
    }

    @Test
    public void adminPageTest() {
        String one = "one";
        String two = "two";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewAllCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        String one = "one";
        String two = "two";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        TestInputOutput tio = new TestInputOutput("123");
        IAction actual = viewAllCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllCountersAction.class, actual);
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
