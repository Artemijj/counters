package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserLoginActionTest {

    private IUserSession userSession = new UserSession();

    private UserLoginAction userLoginAction;

    @BeforeEach
    public void setUp() {
        userLoginAction = new UserLoginAction();
    }

    @Test
    public void userPageTest() {
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("name", "passwd");
        IAction actual = userLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void firstTest() {
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("name", "passwd1");
        IAction actual = userLoginAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }
}
