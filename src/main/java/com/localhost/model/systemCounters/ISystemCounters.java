package com.localhost.model.systemCounters;

import com.localhost.model.CounterType;

import java.util.ArrayList;

public interface ISystemCounters {
    ArrayList<CounterType> getCounterList();
    boolean addCounter(CounterType counterType);
    void deleteCounter(CounterType counterType);
    CounterType getCounter(String counter);
}
