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

public class ViewUserCountersListActionTest {
    private IUserSession userSession = new UserSession();

    private ViewUserCountersAction viewUserCountersAction;

    @BeforeEach
    public void setUp() {
        viewUserCountersAction = new ViewUserCountersAction();
    }

    @Test
    public void adminPageExitTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewUserCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageNameTest() {
        String one = "one";
        String two = "two";
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
            userSession.getAdminSession().linkCounter("user", one);
            userSession.getAdminSession().linkCounter("user", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user");
        IAction actual = viewUserCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserCountersAction.class, actual);
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
