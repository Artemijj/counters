package com.localhost.model.events;

import com.localhost.model.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class EventLogListTest {
    private IEventLog eventLog;

    @Mock
    Event event;

    @BeforeEach
    public void setUp() {
        eventLog = new EventLogList();
    }

    @Test
    public void getEventLogTest() {
        int expectedNumber = 0;
        int actualNumber = eventLog.getEventLog().size();
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

    @Test
    public void nextIdTest() {
        int expectedId = 1;
        int actualId = eventLog.nextId();
        Assertions.assertEquals(expectedId, actualId);
    }
}
