package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
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

public class RegistrationActionTest {
    private IUserSession userSession = new UserSession();

    private RegistrationAction registrationAction;

    @BeforeEach
    public void setUp() {
        registrationAction = new RegistrationAction();
    }

    @Test
    public void registrationEqualPassTest() {
        TestInputOutput tio = new TestInputOutput("name", "pass", "pass", "fio", "+7896321", "address");
        IAction actual = registrationAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserLoginAction.class, actual);
    }

    @Test
    public void registrationNotEqualPassTest() {
        TestInputOutput tio = new TestInputOutput("name", "pass", "pass1");
        IAction actual = registrationAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }

    @Test
    public void registrationNotEqualPassMessageTest() {
        TestInputOutput tio = new TestInputOutput("name", "pass", "pass1");
        registrationAction.execute(userSession, tio);
        String expected = "Пароли не совпадают.";
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
