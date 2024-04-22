package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoiceViewedCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ChoiceViewedCountersAction choiceViewedCountersAction;

    @BeforeEach
    public void setUp() {
        choiceViewedCountersAction = new ChoiceViewedCountersAction();
    }

    @Test
    public void choiceViewedCountersTest() {
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
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceViewedCountersAction.class, actual);
    }

    @Test
    public void choiceViewedWrongCountersTest() {
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
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceViewedCountersAction.class, actual);
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
        IAction actual = choiceViewedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }
}
