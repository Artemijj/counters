package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ViewAllUsersActionTestList {
    private IUserSession userSession = new UserSession();

    private ViewAllUsersAction viewAllUsersAction;

    @BeforeEach
    public void setUp() {
        viewAllUsersAction = new ViewAllUsersAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewAllUsersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        TestInputOutput tio = new TestInputOutput("q");
        IAction actual = viewAllUsersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllUsersAction.class, actual);
    }
}
