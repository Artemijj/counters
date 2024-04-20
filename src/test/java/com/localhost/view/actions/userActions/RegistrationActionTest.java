package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationActionTest {
    private IUserSession userSession = new UserSession();

    private RegistrationAction registrationAction;

    @BeforeEach
    public void setUp() {
        registrationAction = new RegistrationAction();
    }

    @Test
    public void registrationTest() {
        TestInputOutput tio = new TestInputOutput("1");
        IAction actual = registrationAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserLoginAction.class, actual);
    }
}
