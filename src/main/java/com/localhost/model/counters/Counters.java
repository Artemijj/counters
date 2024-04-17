package com.localhost.model.counters;

import com.localhost.model.CounterType;

import java.util.ArrayList;

public class Counters implements ICounters{
    private static ArrayList<CounterType> systemCounters;

    public Counters() {
        systemCounters = new ArrayList<>();
    }

    @Override
    public ArrayList<CounterType> getCounterList() {
        return systemCounters;
    }

    @Override
    public boolean addCounter(CounterType counterType) {
        boolean answer = false;
        if (!systemCounters.contains(counterType)) {
            systemCounters.add(counterType);
            answer = true;
        }
        return answer;
    }

    @Override
    public void deleteCounter(CounterType counterType) {
        systemCounters.remove(counterType);
    }

    @Override
    public CounterType getCounter(String counter) {
        return systemCounters.stream()
                .filter(counterType -> counterType.getCounterTypeName().equals(counter))
                .map(CounterType.class::cast)
                .findFirst()
                .orElse(null);
    }
}
