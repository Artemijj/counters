package com.localhost.view.actions.userActions;

import com.localhost.in.*;
import com.localhost.model.CounterType;
import com.localhost.model.CounterValue;
import com.localhost.model.Record;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.util.Date;

public class ViewCountersByMonthActionTest {
    private IUserSession userSession = new UserSession();

    private ViewCountersByMonthAction viewCountersByMonthAction;

    @BeforeEach
    public void setUp() {
        viewCountersByMonthAction = new ViewCountersByMonthAction();
    }

    @Test
    public void viewCountersByMonthTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date();
        userSession.getModelRecordSet().addRecord(new Record(1, "name", "one", new CounterValue(date, 123)));
        userSession.getModelRecordSet().addRecord(new Record(2, "name", "two", new CounterValue(date, 321)));
        String counterNumber = "0";
        String year = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("0", year, month);
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewCountersByMonthAction.class, actual);
    }

    @Test
    public void viewCountersByMonthWrongCounterTest() {
        CounterType one = new CounterType("one");
        CounterType two = new CounterType("two");
        try {
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        try {
            userSession.getAdminSession().linkCounter("name", one);
            userSession.getAdminSession().linkCounter("name", two);
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        Date date = new Date();
        userSession.getModelRecordSet().addRecord(new Record(1, "name", "one", new CounterValue(date, 123)));
        userSession.getModelRecordSet().addRecord(new Record(2, "name", "two", new CounterValue(date, 321)));
        String counterNumber = "0";
        String year = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear() + "";
        String month = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "";
        TestInputOutput tio = new TestInputOutput("100", year, month);
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewCountersByMonthAction.class, actual);
    }

    @Test
    public void userPageTest() {
        try {
            userSession.getAdminSession().createCounter("one");
            userSession.getAdminSession().createCounter("two");
            userSession.getAdminSession().addUser("name", "passwd");
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
        userSession.logIn("name", "passwd");
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewCountersByMonthAction.execute(userSession, tio);
        Assertions.assertInstanceOf(UserPageAction.class, actual);
    }
}
