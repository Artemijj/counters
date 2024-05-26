package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.in.TestUserSession;
import com.localhost.in.UserSession;
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
import java.util.Date;

@Testcontainers
public class ChoiceSendCounterActionTest {
    @Container
    public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    private IUserSession userSession = new TestUserSession();

    private ChoiceSendCounterAction choiceSendCounterAction;

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        choiceSendCounterAction = new ChoiceSendCounterAction();
    }

    @Test
    public void choiceSendCounterTest() {
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
        TestInputOutput tio = new TestInputOutput("0", "123");
        IAction actual = choiceSendCounterAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceSendCounterAction.class, actual);
    }

    @Test
    public void choiceSendWrongCounterNumberTest() {
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
        TestInputOutput tio = new TestInputOutput("500");
        IAction actual = choiceSendCounterAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceSendCounterAction.class, actual);
    }

    @Test
    public void choiceSendWrongValueCounterTest() {
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
        TestInputOutput tio = new TestInputOutput("0", "qwe");
        IAction actual = choiceSendCounterAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceSendCounterAction.class, actual);
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
        IAction actual = choiceSendCounterAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void wrongCounterNumberMessageTest() {
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
        TestInputOutput tio = new TestInputOutput("qwe");
        choiceSendCounterAction.execute(userSession, tio);
        String expected = "Введите корректный номер счётчика.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void wrongDateMessageTest() {
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
        userSession.getModelRecordSet().addRecord(new Record("name", "one", new CounterValue(new Date(), 123)));
        TestInputOutput tio = new TestInputOutput("0", "321");
        choiceSendCounterAction.execute(userSession, tio);
        String expected = "Показания можно передавать один раз в месяц.";
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
