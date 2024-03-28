package com.localhost.model;

import java.util.ArrayList;

public class Counters {
    private static ArrayList<CounterType> systemCounters;

    public Counters() {
        systemCounters = new ArrayList<>();
    }

    public static ArrayList<CounterType> getCounters() {
        return systemCounters;
    }

    public static boolean addCounter(CounterType counterType) {
        boolean answer = false;
        if (!systemCounters.contains(counterType)) {
            systemCounters.add(counterType);
            answer = true;
        }
        return answer;
    }

    public static void deleteCounter(CounterType counterType) {
        systemCounters.remove(counterType);
    }
}
