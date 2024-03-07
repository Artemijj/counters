package com.localhost.repositories;

import java.util.ArrayList;
import java.util.List;

public class CounterTypes {
    private static final List<String> counterTypes = new ArrayList<>();

    public void addCounterType(String counterName) {
        counterTypes.add(counterName);
    }

    public List<String> getCounterTypes() {
        return counterTypes;
    }
}
