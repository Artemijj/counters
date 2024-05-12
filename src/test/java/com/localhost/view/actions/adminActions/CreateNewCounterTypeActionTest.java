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

public class CreateNewCounterTypeActionTest {
    private IUserSession userSession = new UserSession();

    private CreateNewCounterTypeAction createNewCounterTypeAction;

    @BeforeEach
    public void setUp() {
        createNewCounterTypeAction = new CreateNewCounterTypeAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("counter");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void exitTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void duplicateTest() {
        try {
            userSession.getAdminSession().createCounter("counter");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("counter");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
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
