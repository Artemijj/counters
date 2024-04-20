package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class RecordTest {
    private Record record;
    @Mock
    User user;
    private String counterType = "type";
    @Mock
    CounterValue counterValue;

    @BeforeEach
    public void setUp() {
        record = new Record(1, "newUser", counterType, counterValue);
    }

    @Test
    public void getIdTest() {
        int expectedId = 1;
        int actualId = record.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void getLoginTest() {
        String expectedUser = "newUser";
        String actualUser = record.getLogin();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getCounterTypeTest() {
        String expectedCounterType = counterType;
        String actualCounterType = record.getCounterType();
        Assertions.assertEquals(expectedCounterType, actualCounterType);
    }

    @Test
    public void getCounterValueTest() {
        CounterValue expectedCounterValue = counterValue;
        CounterValue actualCounterValue = record.getCounterValue();
        Assertions.assertEquals(expectedCounterValue, actualCounterValue);
    }
}
