package com.localhost.view.actions.userActions;

import com.localhost.in.IUserSession;
import com.localhost.in.UserSession;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.FirstAction;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserPageActionTest {
    private IUserSession userSession = new UserSession();

    private UserPageAction userPageAction;

    @BeforeEach
    public void SetUp() {
        userPageAction = new UserPageAction();
    }

    @Test
    public void choiceSendCounterTest() {
        TestInputOutput tio = new TestInputOutput("1");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceSendCounterAction.class, actual);
    }

    @Test
    public void choiceViewedCountersTest() {
        TestInputOutput tio = new TestInputOutput("2");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceViewedCountersAction.class, actual);
    }

    @Test
    public void viewCountersByMonthTest() {
        TestInputOutput tio = new TestInputOutput("3");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewCountersByMonthAction.class, actual);
    }

    @Test
    public void viewActualCountersTest() {
        TestInputOutput tio = new TestInputOutput("4");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewActualCountersAction.class, actual);
    }

    @Test
    public void choiceLinkedCountersTest() {
        TestInputOutput tio = new TestInputOutput("5");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceLinkedCountersAction.class, actual);
    }

    @Test
    public void choiceUnlinkedCountersTest() {
        TestInputOutput tio = new TestInputOutput("6");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ChoiceUnlinkedCountersAction.class, actual);
    }

    @Test
    public void firstTest() {
        TestInputOutput tio = new TestInputOutput("q");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(FirstAction.class, actual);
    }

    @Test
    public void defaultTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = userPageAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }

    @Test
    public void messageTest() {
        TestInputOutput tio = new TestInputOutput("qwe");
        userPageAction.execute(userSession, tio);
        String expected = "Введено некорректное значение.";
        String actual = tio.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}
