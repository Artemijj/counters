package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.DBCPDataSourceFactory;
import com.localhost.view.IInputOutput;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddAdminActionTest {
    private IUserSession userSession = new UserSession();

    private AddAdminAction addAdminAction;

    @BeforeEach
    public void setUp() {
        addAdminAction = new AddAdminAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("newAdmin", "pass");
        IAction actual = addAdminAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void wrongMessageTest() {
        try {
            userSession.getAdminSession().addAdmin("admin", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("admin", "pass");
        TestInputOutput tio = new TestInputOutput("admin");
        String expected = "Пользователь с таким именем существует.\n" +
                        "Введите другое имя администратора.";
        addAdminAction.execute(userSession, tio);
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
