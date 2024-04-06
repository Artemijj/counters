package com.localhost.model;

import java.util.Date;

public class Event {
    private int id;
    private User user;
    private Date date;
    private String activity;

    public Event(int id, User user, Date date, String activity) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }
}
