package model;

import java.time.LocalDateTime;

public class Event {
    private String title;
    private String category;
    private String description;
    private LocalDateTime dateTime;

    public Event(String title, String category, String description, LocalDateTime dateTime) {
        this.title = title;
        this.category = category;
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return title + " (" + category + ") - " + dateTime.toString() + " | " + description;
    }
}
