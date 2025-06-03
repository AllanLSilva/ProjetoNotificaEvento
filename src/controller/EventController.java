package controller;

import model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EventController {
    private List<Event> events;

    public EventController(List<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public List<Event> getAllEvents() {
        return events;
    }

    public List<Event> getUpcomingEvents() {
        return events.stream()
                .filter(e -> e.getDateTime().isAfter(LocalDateTime.now()))
                .sorted(Comparator.comparing(Event::getDateTime))
                .collect(Collectors.toList());
    }

    public List<Event> getOngoingEvents() {
        LocalDateTime now = LocalDateTime.now();
        return events.stream()
                .filter(e -> e.getDateTime().isBefore(now.plusHours(1)) && e.getDateTime().isAfter(now.minusHours(2)))
                .collect(Collectors.toList());
    }

    public List<Event> getPastEvents() {
        return events.stream()
                .filter(e -> e.getDateTime().isBefore(LocalDateTime.now()))
                .sorted(Comparator.comparing(Event::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}
