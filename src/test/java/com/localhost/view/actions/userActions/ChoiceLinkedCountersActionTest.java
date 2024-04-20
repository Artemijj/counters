package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChoiceLinkedCountersActionTest {
    private IUserSession userSession = new UserSession();

    private ChoiceLinkedCountersAction choiceLinkedCountersAction;

    @BeforeEach
    public void setUp() {
        choiceLinkedCountersAction = new ChoiceLinkedCountersAction();
    }

    @Test
    public void choiceLinkedCountersTest() {
        try {
            userSession.getAdminSession().createCounter("one");
            userSession.getAdminSession().createCounter("two");
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        TestInputOutput tio = new TestInputOutput("0");
        IAction actual = choiceLinkedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceLinkedCountersAction.class, actual);
    }

    @Test
    public void userPageTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = choiceLinkedCountersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }
}
