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

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
