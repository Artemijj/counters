package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewUserCountersListActionTest {
    private IUserSession userSession = new UserSession();

    private ViewUserCountersAction viewUserCountersAction;

    @BeforeEach
    public void setUp() {
        viewUserCountersAction = new ViewUserCountersAction();
    }

    @Test
    public void adminPageExitTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewUserCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void adminPageNameTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        userSession.getModelSystemCounters().addCounter(one);
        userSession.getModelSystemCounters().addCounter(two);
        try {
            userSession.getAdminSession().addUser("user", "passwd");
            userSession.getAdminSession().linkCounter("user", one);
            userSession.getAdminSession().linkCounter("user", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user");
        IAction actual = viewUserCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserCountersAction.class, actual);
    }
}
