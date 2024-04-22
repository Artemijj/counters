package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
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
    public void registrationEqualPassTest() {
        TestInputOutput tio = new TestInputOutput("name", "pass", "pass", "fio", "+7896321", "address");
        IAction actual = registrationAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserLoginAction.class, actual);
    }

    @Test
    public void registrationNotEqualPassTest() {
        TestInputOutput tio = new TestInputOutput("name", "pass", "pass1", "fio", "+7896321", "address");
        IAction actual = registrationAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }
}
