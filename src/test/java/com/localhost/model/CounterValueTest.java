package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;

public class CounterValueTest {
    CounterValue counterValue;

    @Mock
    Date date;

    @BeforeEach
    public void setUp() {
        counterValue = new CounterValue(date, 1);
    }

    @Test
    public void getDateTest() {
        Date expectedDate = date;
        Date actualDate = counterValue.getDate();
        Assertions.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void getValueTest() {
        int expectedValue = 1;
        int actualValue = counterValue.getValue();
        Assertions.assertEquals(expectedValue, actualValue);
    }
}
