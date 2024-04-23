package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnlinkedUserCountersTypeActionTest {
    private IUserSession userSession = new UserSession();

    private UnlinkedUserCountersTypeAction unlinkedUserCountersTypeAction;

    @BeforeEach
    public void setUp() {
        unlinkedUserCountersTypeAction = new UnlinkedUserCountersTypeAction();
    }

    @Test
    public void unlinkedUserCountersTypeTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelCounters().addCounter(one);
        userSession.getModelCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user", "null");
        IAction actual = unlinkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UnlinkedUserCountersTypeAction.class, actual);
    }

    @Test
    public void adminTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelCounters().addCounter(one);
        userSession.getModelCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        try {
            userSession.getAdminSession().linkCounter("user", one);
            userSession.getAdminSession().linkCounter("user", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user", "0");
        IAction actual = unlinkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void linkedUserCountersTypeMessageTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelCounters().addCounter(one);
        userSession.getModelCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
            userSession.getAdminSession().linkCounter("user", one);
            userSession.getAdminSession().linkCounter("user", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        String wrongNumber = Integer.MAX_VALUE + "";
        TestInputOutput tio = new TestInputOutput("user", wrongNumber);
        unlinkedUserCountersTypeAction.execute(userSession, tio);
        String expected = "Введите корректный номер счётчика.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
