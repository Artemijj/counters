package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserLoginActionTest {

    private IUserSession userSession = new UserSession();

    private UserLoginAction userLoginAction;

    @BeforeEach
    public void setUp() {
        userLoginAction = new UserLoginAction();
    }

    @Test
    public void userPageTest() {
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("name", "passwd");
        IAction actual = userLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void firstTest() {
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("name", "passwd1");
        IAction actual = userLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
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
