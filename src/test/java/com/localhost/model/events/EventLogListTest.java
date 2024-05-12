package com.localhost.model.events;

import com.localhost.model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class EventLogListTest {
    private IEventLog eventLog;

    Event event = new Event("login", new Date(), "activity");

    @BeforeEach
    public void setUp() {
        eventLog = new EventLogList();
    }

    @Test
    public void getEventLogTest() {
        int expectedNumber = 0;
        int actualNumber = eventLog.getEventLogList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addEventTrueTest() {
        boolean actual = eventLog.addEvent(event);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addEventFalseTest() {
        eventLog.addEvent(event);
        boolean actual = eventLog.addEvent(event);
        Assertions.assertFalse(actual);
    }
//
//    @Test
//    public void nextIdTest() {
//        int expectedId = 1;
//        int actualId = Tools.nextId(eventLog.getEventLogList());
//        Assertions.assertEquals(expectedId, actualId);
//    }
}
