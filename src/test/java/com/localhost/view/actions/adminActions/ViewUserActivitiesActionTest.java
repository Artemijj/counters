package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.dbcp.DBCPDataSourceFactory;
import com.localhost.model.Event;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ViewUserActivitiesActionTest {
    private IUserSession userSession = new UserSession();

    private ViewUserActivitiesAction viewUserActivitiesAction;// = new ViewUserActivitiesAction();

    @BeforeEach
    public void setUp() {
        viewUserActivitiesAction = new ViewUserActivitiesAction();
    }

    @Test
    public void adminPageExitTest() {
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewUserActivitiesAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.getModelEventLog().addEvent(new Event("user", new Date(), "event"));
        TestInputOutput tio = new TestInputOutput("user");
        IAction actual = viewUserActivitiesAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserActivitiesAction.class, actual);
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
