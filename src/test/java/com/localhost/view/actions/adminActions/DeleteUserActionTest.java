package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeleteUserActionTest {
    private IUserSession userSession = new UserSession();

    private DeleteUserAction deleteUserAction;

    @BeforeEach
    public void setUp() {
        deleteUserAction = new DeleteUserAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("user");
        IAction actual = deleteUserAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
