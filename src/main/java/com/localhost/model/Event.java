package com.localhost.model;

import java.util.Date;

public class Event implements Id{
    private int id;
    private String login;
    private Date date;
    private String activity;

    public Event(int id, String login, Date date, String activity) {
        this.id = id;
        this.login = login;
        this.date = date;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public Date getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }
}
