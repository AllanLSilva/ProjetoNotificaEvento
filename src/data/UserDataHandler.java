package data;

import model.Event;
import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataHandler {
    public static User loadOrCreateUser(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.data"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(email)) {
                    return new User(parts[1], parts[0], parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Usuário não encontrado. Criando novo usuário.");
        return new User("Novo Usuário", email, "000000000");
    }

    public static void saveConfirmedEvents(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("confirmed.data"))) {
            for (Event event : user.getConfirmedEvents()) {
                writer.write(user.getEmail() + ";" + event.getTitle());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadConfirmedEvents(User user, List<Event> allEvents) {
        try (BufferedReader reader = new BufferedReader(new FileReader("confirmed.data"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 2 && parts[0].equalsIgnoreCase(user.getEmail())) {
                    for (Event e : allEvents) {
                        if (e.getTitle().equalsIgnoreCase(parts[1])) {
                            user.addConfirmedEvent(e);
                        }
                    }
                }
            }
        } catch (IOException e) {
            // Ignorar se o arquivo não existir
        }
    }
}
