package com.localhost.model.systemCounters;

import com.localhost.model.CounterType;

import java.util.ArrayList;

public class SystemCountersList implements ISystemCounters {
    private static ArrayList<CounterType> systemCounters;

    public SystemCountersList() {
        systemCounters = new ArrayList<>();
    }

    @Override
    public ArrayList<CounterType> getCounterList() {
        return systemCounters;
    }

    @Override
    public boolean addCounter(CounterType counterType) {
        boolean answer = false;
        long counterTypeCount = systemCounters.stream()
                .filter(cT -> cT.getCounterTypeName().equals(counterType.getCounterTypeName()))
                .count();
        if ((counterTypeCount == 0) && (!systemCounters.contains(counterType))) {
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
