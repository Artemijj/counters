package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class RecordTest {
    private Record record;
    @Mock
    User user;
    @Mock
    CounterType counterType;
    @Mock
    CounterValue counterValue;

    @BeforeEach
    public void setUp() {
        record = new Record(1, user, counterType, counterValue);
    }

    @Test
    public void getIdTest() {
        int expectedId = 1;
        int actualId = record.getId();
        Assertions.assertEquals(expectedId, actualId);
    }

    @Test
    public void getUserTest() {
        User expectedUser = user;
        User actualUser = record.getUser();
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getCounterTypeTest() {
        CounterType expectedCounterType = counterType;
        CounterType actualCounterType = record.getCounterType();
        Assertions.assertEquals(expectedCounterType, actualCounterType);
    }

    @Test
    public void getCounterValueTest() {
        CounterValue expectedCounterValue = counterValue;
        CounterValue actualCounterValue = record.getCounterValue();
        Assertions.assertEquals(expectedCounterValue, actualCounterValue);
    }
}
