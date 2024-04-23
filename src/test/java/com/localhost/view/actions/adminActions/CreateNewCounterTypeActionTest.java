package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateNewCounterTypeActionTest {
    private IUserSession userSession = new UserSession();

    private CreateNewCounterTypeAction createNewCounterTypeAction;

    @BeforeEach
    public void setUp() {
        createNewCounterTypeAction = new CreateNewCounterTypeAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("counter");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void exitTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void duplicateTest() {
        try {
            userSession.getAdminSession().createCounter("counter");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("counter");
        IAction actual = createNewCounterTypeAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
