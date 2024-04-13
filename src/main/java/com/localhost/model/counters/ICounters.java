package com.localhost.model.counters;

import com.localhost.model.CounterType;

import java.util.ArrayList;

public interface ICounters {
    ArrayList<CounterType> getCounterList();
    boolean addCounter(CounterType counterType);
    void deleteCounter(CounterType counterType);
    CounterType getCounter(String counter);
}
