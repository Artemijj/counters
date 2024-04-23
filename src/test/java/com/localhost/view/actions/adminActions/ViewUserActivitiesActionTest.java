package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.Event;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ViewUserActivitiesActionTest {
    private IUserSession userSession = new UserSession();

    private ViewUserActivitiesAction viewUserActivitiesAction;// = new ViewUserActivitiesAction();

    @BeforeEach
    public void setUp() {
        viewUserActivitiesAction = new ViewUserActivitiesAction();
    }

    @Test
    public void adminPageExitTest() {
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewUserActivitiesAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        try {
            userSession.getAdminSession().addUser("user", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.getModelEventLog().addEvent(new Event(1,"user", new Date(), "event"));
        TestInputOutput tio = new TestInputOutput("user");
        IAction actual = viewUserActivitiesAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserActivitiesAction.class, actual);
    }
}
