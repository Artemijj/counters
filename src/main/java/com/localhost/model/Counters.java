package com.localhost.model;

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
}
