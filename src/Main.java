import controller.EventController;
import data.EventDataHandler;
import data.UserDataHandler;
import model.Event;
import model.User;
import view.Menu;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Event> events = EventDataHandler.loadEvents("events.data");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite seu e-mail:");
        String email = scanner.nextLine();

        User user = UserDataHandler.loadOrCreateUser(email);
        EventController controller = new EventController(events);

        UserDataHandler.loadConfirmedEvents(user, events);
        Menu menu = new Menu(controller, user);
        menu.show();
        UserDataHandler.saveConfirmedEvents(user);
    }
}
