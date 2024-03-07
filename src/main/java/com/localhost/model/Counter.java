package com.localhost.model;

import java.util.Date;
import java.util.HashMap;

public class Counter {
    private String nameCounter;
    private HashMap<Date, Integer> counterData;

    public Counter(String nameCounter) {
        this.nameCounter = nameCounter;
        counterData = new HashMap<>();
    }

    public void addReadings(Date date, Integer readings) {
        counterData.put(date, readings);
    }

    public HashMap<Date, Integer> getCounterData() {
        return counterData;
    }

    public Integer getReadingsByDate(Date date) {
        return counterData.get(date);
    }
}
