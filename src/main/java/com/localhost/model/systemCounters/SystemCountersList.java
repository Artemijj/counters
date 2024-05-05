package com.localhost.model.systemCounters;


import java.util.ArrayList;

public class SystemCountersList implements ISystemCounters {
    private static ArrayList<String> systemCounters;

    public SystemCountersList() {
        systemCounters = new ArrayList<>();
    }

    @Override
    public ArrayList<String> getCounterList() {
        return systemCounters;
    }

    @Override
    public boolean addCounter(String counterType) {
        boolean answer = false;
//        long counterTypeCount = systemCounters.stream()
//                .filter(cT -> cT.getCounterTypeName().equals(counterType.getCounterTypeName()))
//                .count();
        if (!systemCounters.contains(counterType)) {
            systemCounters.add(counterType);
            answer = true;
        }
        return answer;
    }

    @Override
    public void deleteCounter(String counterType) {
        systemCounters.remove(counterType);
    }

//    @Override
//    public CounterType getCounter(String counter) {
//        return systemCounters.stream()
//                .filter(counterType -> counterType.getCounterTypeName().equals(counter))
//                .map(CounterType.class::cast)
//                .findFirst()
//                .orElse(null);
//    }
}
