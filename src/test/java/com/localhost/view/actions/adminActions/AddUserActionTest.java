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
        TestInputOutput tio = new TestInputOutput("user", "pass");
        IAction actual = addUserAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void wrongMessageTest() {
        try {
            userSession.getAdminSession().addUser("user", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("user", "pass");
        TestInputOutput tio = new TestInputOutput("user");
        String expected = "Пользователь с таким именем существует.\n" +
                        "Введите другое имя пользователя.";
        addUserAction.execute(userSession, tio);
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
