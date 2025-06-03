package data;

import model.Event;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDataHandler {
    public static List<Event> loadEvents(String filename) {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    String title = parts[0];
                    String category = parts[1];
                    String description = parts[2];
                    LocalDateTime dateTime = LocalDateTime.parse(parts[3]);
                    events.add(new Event(title, category, description, dateTime));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    public static void saveEvents(String filename, List<Event> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Event event : events) {
                writer.write(String.join(";", event.getTitle(), event.getCategory(), event.getDescription(), event.getDateTime().toString()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
