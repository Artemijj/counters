package com.localhost.model.events;

import com.localhost.model.Event;

import java.util.ArrayList;
import java.util.Comparator;

public class EventLog implements IEventLog{
    private static ArrayList<Event> events;

    public EventLog() {
        events = new ArrayList<>();
    }

    @Override
    public ArrayList<Event> getEventLog() {
        return events;
    }

    @Override
    public boolean addEvent(Event event) {
        boolean answer = false;
        if (!events.contains(event)) {
            events.add(event);
            answer = true;
        }
        return answer;
    }

    @Override
    public int nextId() {
        return (events.size() == 0) ? 1 : events.stream().map(Event::getId).max(Comparator.naturalOrder()).get() + 1;
    }
}