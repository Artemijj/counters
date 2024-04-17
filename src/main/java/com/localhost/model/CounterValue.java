package com.localhost.model;

import java.util.Date;

public class CounterValue {
    private Date date;
    private Integer value;

    public CounterValue(Date date, Integer value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public Integer getValue() {
        return value;
    }
}
