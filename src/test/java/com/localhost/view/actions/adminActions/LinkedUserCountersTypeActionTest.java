package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedUserCountersTypeActionTest {
    private IUserSession userSession = new UserSession();

    private LinkedUserCountersTypeAction linkedUserCountersTypeAction;

    @BeforeEach
    public void setUp() {
        linkedUserCountersTypeAction = new LinkedUserCountersTypeAction();
    }

    @Test
    public void linkedUserCountersTypeTest() {
        TestInputOutput tio = new TestInputOutput("user", "null");
        IAction actual = linkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(LinkedUserCountersTypeAction.class, actual);
    }

    @Test
    public void adminPageTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelCounters().addCounter(one);
        userSession.getModelCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user", "0");
        IAction actual = linkedUserCountersTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
