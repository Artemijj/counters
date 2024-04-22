package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.IInputOutput;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AddAdminActionTest {
    private IUserSession userSession = new UserSession();

    private AddAdminAction addAdminAction;

    @BeforeEach
    public void setUp() {
        addAdminAction = new AddAdminAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("newAdmin", "pass");
        IAction actual = addAdminAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void wrongMessageTest() {
        try {
            userSession.getAdminSession().addAdmin("admin", "pass");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("admin", "pass");
        TestInputOutput tio = new TestInputOutput("admin");
        String expected = "Пользователь с таким именем существует.\n +" +
                        "Введите другое имя администратора.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
//        IAction actual = addAdminAction.execute(userSession, tio);
//        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
