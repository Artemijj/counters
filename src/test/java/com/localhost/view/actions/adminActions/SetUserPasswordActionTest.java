package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUserPasswordActionTest {
    private IUserSession userSession = new UserSession();

    private SetUserPasswordAction setUserPasswordAction;

    @BeforeEach
    public void setUp() {
        setUserPasswordAction = new SetUserPasswordAction();
    }

    @Test
    public void adminPageTest() {
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("user", "newPass");
        IAction actual = setUserPasswordAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
