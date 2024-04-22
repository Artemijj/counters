package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddUserActionTest {
    private IUserSession userSession = new UserSession();

    private AddUserAction addUserAction;

    @BeforeEach
    public void setUp() {
        addUserAction = new AddUserAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("newAdmin", "pass");
        IAction actual = addUserAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
