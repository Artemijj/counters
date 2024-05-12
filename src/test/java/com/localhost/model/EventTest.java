package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

public class EventTest {

    private Event event;
    @Mock
    Date date;

    @BeforeEach
    public void setUp() {
        event = new Event("name", date, "activity");
    }

//    @Test
//    public void getIdTest() {
//        int expectedId = 1;
//        int actualId = event.getId();
//        Assertions.assertEquals(expectedId, actualId);
//    }

    @Test
    public void getLoginTest() {
        String expectedLogin = "name";
        String actualLogin = event.getLogin();
        Assertions.assertEquals(expectedLogin, actualLogin);
    }

    @Test
    public void getDateTest() {
        Date expectedDate = date;
        Date actualDate = event.getDate();
        Assertions.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getActivityTest() {
        String expectedActivity = "activity";
        String actualActivity = event.getActivity();
        Assertions.assertEquals(expectedActivity, actualActivity);
    }
}
