package com.localhost.model.counters;

import com.localhost.model.CounterType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CountersTest {
    private ICounters counters;

    @Mock
    CounterType counterType;

    @BeforeEach
    public void setUp() {
        counters = new Counters();
    }

    @Test
    public void getCounterListTest() {
        int expectedNumber = 0;
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void addCounterTrueTest() {
        boolean actual = counters.addCounter(counterType);
        Assertions.assertTrue(actual);
    }

    @Test
    public void addCounterFalseTest() {
        counters.addCounter(counterType);
        boolean actual = counters.addCounter(counterType);
        Assertions.assertFalse(actual);
    }

    @Test
    public void deleteCounterTest() {
        int expectedNumber = 0;
        counters.addCounter(counterType);
        counters.deleteCounter(counterType);
        int actualNumber = counters.getCounterList().size();
        Assertions.assertEquals(expectedNumber, actualNumber);
    }

    @Test
    public void getCounterTest() {
        CounterType newCounterType = new CounterType("NewCounterType");
        counters.addCounter(newCounterType);
        CounterType expectedType = newCounterType;
        CounterType actualType = counters.getCounter("NewCounterType");
        Assertions.assertEquals(expectedType, actualType);
    }
}
