package com.localhost.view.actions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.adminActions.AdminLoginAction;
import com.localhost.view.actions.userActions.RegistrationAction;
import com.localhost.view.actions.userActions.UserLoginAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FirstActionTest {
    private IUserSession userSession = new UserSession();

    private FirstAction firstAction;

    @BeforeEach
    public void setUp() {
        firstAction = new FirstAction();
    }

    @Test
    public void registrationTest() {
        TestInputOutput tio = new TestInputOutput("1");
        IAction actual = firstAction.execute(userSession, tio);
        Assertions.assertInstanceOf(RegistrationAction.class, actual);
    }

    @Test
    public void userLoginTest() {
        TestInputOutput tio = new TestInputOutput("2");
        IAction actual = firstAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserLoginAction.class, actual);
    }

    @Test
    public void adminLoginTest() {
        TestInputOutput tio = new TestInputOutput("3");
        IAction actual = firstAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminLoginAction.class, actual);
    }

    @Test
    public void finallyTest() {
        TestInputOutput tio = new TestInputOutput("q");
        IAction actual = firstAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FinallyAction.class, actual);
    }
}
