package com.localhost.view.actions.adminActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.in.TestUserSession;
import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class ViewUserRecordByTypeDateActionTest {
    @Container
    public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    private IUserSession userSession = new TestUserSession();

    private ViewUserRecordByTypeDateAction viewUserRecordByTypeDateAction;

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        viewUserRecordByTypeDateAction = new ViewUserRecordByTypeDateAction();
    }

    @Test
    public void adminPageLoginExitTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageTypeExitTest() {
        TestInputOutput tio = new TestInputOutput("user", "p");
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageYearExitTest() {
        TestInputOutput tio = new TestInputOutput("user", "type", "p");
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageMonthExitTest() {
        String year = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        TestInputOutput tio = new TestInputOutput("user", "type", year, "p");
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageNoSuchElementTest() {
        String year = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("user", "type", year, month);
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void messageTest() {
        String year = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("user", "type", year, month);
        viewUserRecordByTypeDateAction.execute(userSession, tio);
        String expected = "Показания за этот месяц не передавались.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void adminPageTest() {
        userSession.getModelSystemCounters().addCounter("type");
        try {
            userSession.getAdminSession().addUser("user", "passwd");
            userSession.getAdminSession().linkCounter("user", "type");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.getModelRecordSet().addRecord(new Record("user", "type", new CounterValue(new Date(), 1)));

        String year = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("user", "type", year, month);
        IAction actual = viewUserRecordByTypeDateAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @AfterEach
    public void clearDB() {
        String events = "DELETE FROM counter.events";
        String records = "DELETE FROM counter.records";
        String systemCounters = "DELETE FROM counter.system_counters";
        String userCounters = "DELETE FROM counter.user_counters";
        String users = "DELETE FROM counter.users";
        try (Statement stmt = connection.createStatement()) {
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