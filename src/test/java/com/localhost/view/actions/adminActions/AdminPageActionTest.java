package com.localhost.view.actions.adminActions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminPageActionTest {

    private IUserSession userSession = new UserSession();

    private AdminPageAction adminPageAction;

    @BeforeEach
    public void setUp() {
        adminPageAction = new AdminPageAction();
    }

    @Test
    public void addUserTest() {
        TestInputOutput tio = new TestInputOutput("1");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AddUserAction.class, actual);
    }

    @Test
    public void deleteUserTest() {
        TestInputOutput tio = new TestInputOutput("2");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(DeleteUserAction.class, actual);
    }

    @Test
    public void addAdminTest() {
        TestInputOutput tio = new TestInputOutput("3");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AddAdminAction.class, actual);
    }

    @Test
    public void viewAllUsersTest() {
        TestInputOutput tio = new TestInputOutput("4");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllUsersAction.class, actual);
    }

    @Test
    public void viewAllCountersTest() {
        TestInputOutput tio = new TestInputOutput("5");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllCountersAction.class, actual);
    }

    @Test
    public void createNewCounterTypeTest() {
        TestInputOutput tio = new TestInputOutput("6");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(CreateNewCounterTypeAction.class, actual);
    }

    @Test
    public void viewUserActivitiesTest() {
        TestInputOutput tio = new TestInputOutput("7");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserActivitiesAction.class, actual);
    }

    @Test
    public void viewUserCountersTest() {
        TestInputOutput tio = new TestInputOutput("8");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewUserCountersAction.class, actual);
    }

    @Test
    public void linkedUserCountersTypeTest() {
        TestInputOutput tio = new TestInputOutput("9");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(LinkedUserCountersTypeAction.class, actual);
    }

    @Test
    public void unlinkedUserCountersTypeTest() {
        TestInputOutput tio = new TestInputOutput("10");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UnlinkedUserCountersTypeAction.class, actual);
    }

    @Test
    public void setUserPasswordTest() {
        TestInputOutput tio = new TestInputOutput("11");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(SetUserPasswordAction.class, actual);
    }

    @Test
    public void firstTest() {
        TestInputOutput tio = new TestInputOutput("q");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }

    @Test
    public void defaultTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = adminPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }
}
