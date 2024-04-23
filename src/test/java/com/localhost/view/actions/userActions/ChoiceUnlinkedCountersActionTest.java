package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoiceUnlinkedCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ChoiceUnlinkedCountersAction choiceUnlinkedCountersAction;

    @BeforeEach
    public void setUp() {
        choiceUnlinkedCountersAction = new ChoiceUnlinkedCountersAction();
    }

    @Test
    public void choiceUnlinkedCountersTest() {
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
        TestInputOutput tio = new TestInputOutput("0");
        IAction actual = choiceUnlinkedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceUnlinkedCountersAction.class, actual);
    }

    @Test
    public void choiceUnlinkedWrongCountersTest() {
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
        TestInputOutput tio = new TestInputOutput("3");
        IAction actual = choiceUnlinkedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceUnlinkedCountersAction.class, actual);
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
        IAction actual = choiceUnlinkedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void messageTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        TestInputOutput tio = new TestInputOutput("3");
        choiceUnlinkedCountersAction.execute(userSession, tio);
        String expected = "Введите корректный номер счётчика.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
