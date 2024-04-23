package com.localhost.view.actions.userActions;

import com.localhost.in.AdminException;
import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ChoiceSendCounterActionTest {
    private IUserSession userSession = new UserSession();

    private ChoiceSendCounterAction choiceSendCounterAction;

    @BeforeEach
    public void setUp() {
        choiceSendCounterAction = new ChoiceSendCounterAction();
    }

    @Test
    public void choiceSendCounterTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
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
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
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
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
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
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
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
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.getModelRecordSet().addRecord(new Record(1, "name", "one", new CounterValue(new Date(), 123)));
        TestInputOutput tio = new TestInputOutput("0", "321");
        choiceSendCounterAction.execute(userSession, tio);
        String expected = "Показания можно передавать один раз в месяц.";
        String actual =tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
