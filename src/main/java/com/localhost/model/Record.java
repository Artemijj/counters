package com.localhost.model;

public class Record {
    private Integer id;
    private User user;
    private CounterType counterType;
    private CounterValue counterValue;

    public Record(Integer id, User user, CounterType counterType, CounterValue counterValue) {
        this.id = id;
        this.user = user;
        this.counterType = counterType;
        this.counterValue = counterValue;
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public CounterType getCounterType() {
        return counterType;
    }

    public CounterValue getCounterValue() {
        return counterValue;
    }
}
