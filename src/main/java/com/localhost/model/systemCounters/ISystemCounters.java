package com.localhost.model.systemCounters;

import java.util.ArrayList;

public interface ISystemCounters {
    ArrayList<String> getCounterList();
    boolean addCounter(String counterType);
    void deleteCounter(String counterType);
//    CounterType getCounter(String counter);
}
