package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminLoginActionTest {
    private IUserSession userSession = new UserSession();

    private AdminLoginAction adminLoginAction;

    @BeforeEach
    public void setUp() {
        adminLoginAction = new AdminLoginAction();
    }

    @Test
    public void adminPageTest() {
        try {
            userSession.getAdminSession().addAdmin("admin", "admin");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("admin", "admin");
        IAction actual = adminLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void firstTest() {
        try {
            userSession.getAdminSession().addAdmin("admin", "admin");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("admin", "admin1");
        IAction actual = adminLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }
}
