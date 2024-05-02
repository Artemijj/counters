package com.localhost.model;

public class UserCounter {
    private Integer id;
    private String login;
    private String counterName;

    public UserCounter(int id, String login, String counterName) {
        this.id = id;
        this.login = login;
        this.counterName = counterName;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getCounterName() {
        return counterName;
    }
}
