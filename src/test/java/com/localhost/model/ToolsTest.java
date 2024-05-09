package com.localhost.model;

import com.localhost.model.events.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToolsTest {

    IEventLog eventLog = new EventLogList();
    @Test
    public void parseCorrectNumberTest() {
        int expectedNumber = 1;
        int actualNumber = Tools.parse("1");
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void parseAnySymbolTest() {
        int expectedNumber = Integer.MAX_VALUE;
        int actualNumber = Tools.parse("q");
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void nextIdTest() {
        int expectedId = 1;
        int actualId = Tools.nextId(eventLog.getEventLogList());
        Assertions.assertEquals(expectedId, actualId);
    }
}
