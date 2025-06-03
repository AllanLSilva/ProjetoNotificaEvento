package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String phone;
    private List<Event> confirmedEvents = new ArrayList<>();

    public User(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public List<Event> getConfirmedEvents() {
        return confirmedEvents;
    }

    public void addConfirmedEvent(Event event) {
        if (!confirmedEvents.contains(event)) {
            confirmedEvents.add(event);
        }
    }
}
