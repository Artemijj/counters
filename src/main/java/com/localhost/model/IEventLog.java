package com.localhost.model;

import java.util.ArrayList;

public interface IEventLog {
    ArrayList<Event> getEventLog();
    boolean addEvent(Event event);
    int nextId();
}
