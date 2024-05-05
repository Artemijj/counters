package com.localhost.model.events;

import com.localhost.model.Event;

import java.util.ArrayList;

public interface IEventLog {
    ArrayList<Event> getEventLogList();
    boolean addEvent(Event event);
//    int nextId();
}
