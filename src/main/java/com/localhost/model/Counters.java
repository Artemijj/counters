package com.localhost.model;

import java.util.ArrayList;

public class Counter {
    private String nameCounter;
    private ArrayList<CounterType> counters;

    public Counter(String nameCounter) {
        this.nameCounter = nameCounter;
        counters = new ArrayList<>();
    }

    public String getNameCounter() {
        return nameCounter;
    }

    public void setNameCounter(String nameCounter) {
        this.nameCounter = nameCounter;
    }

    public ArrayList<CounterType> getCounters() {
        return counters;
    }

    public void setCounters(ArrayList<CounterType> counters) {
        this.counters = counters;
    }
}
