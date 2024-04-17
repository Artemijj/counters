package com.localhost.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CounterTypeTest {
    CounterType counterType = new CounterType("CounterTypeName");

    @Test
    public void getCounterTypeNameTest() {
        String expectedName = "CounterTypeName";
        String actualName = counterType.getCounterTypeName();
        Assertions.assertEquals(expectedName, actualName);
    }
}
