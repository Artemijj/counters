package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewAllCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ViewAllCountersAction viewAllCountersAction;

    @BeforeEach
    public void setUp() {
        viewAllCountersAction = new  ViewAllCountersAction();
    }

    @Test
    public void adminPageTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewAllCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        TestInputOutput tio = new TestInputOutput("123");
        IAction actual = viewAllCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllCountersAction.class, actual);
    }
}
