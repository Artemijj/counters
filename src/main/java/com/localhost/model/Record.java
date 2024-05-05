package com.localhost.model;

public class Record implements Id{
    private Integer id;
    private String login;
    private String counterType;
    private CounterValue counterValue;

    public Record(Integer id, String login, String counterType, CounterValue counterValue) {
        this.id = id;
        this.login = login;
        this.counterType = counterType;
        this.counterValue = counterValue;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getCounterType() {
        return counterType;
    }

    public CounterValue getCounterValue() {
        return counterValue;
    }
}
