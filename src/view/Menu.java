package view;

import controller.EventController;
import data.EventDataHandler;
import model.Event;
import model.User;

import java.time.LocalDateTime;
import java.util.*;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private EventController eventController;
    private User user;

    public Menu(EventController eventController, User user) {
        this.eventController = eventController;
        this.user = user;
    }

    public void show() {
        int option;
        do {
            System.out.println("\n1. Ver eventos futuros");
            System.out.println("2. Ver eventos atuais");
            System.out.println("3. Ver eventos passados");
            System.out.println("4. Confirmar presença");
            System.out.println("5. Cancelar presença");
            System.out.println("6. Criar novo evento");
            System.out.println("7. Ver eventos com presença confirmada");
            System.out.println("0. Sair");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> listar(eventController.getUpcomingEvents());
                case 2 -> listar(eventController.getOngoingEvents());
                case 3 -> listar(eventController.getPastEvents());
                case 4 -> confirmarPresenca();
                case 5 -> cancelarPresenca();
                case 6 -> criarEvento();
                case 7 -> listarPresencasConfirmadas();
            }
        } while (option != 0);

        EventDataHandler.saveEvents("events.data", eventController.getAllEvents());
    }

    private void listar(List<Event> eventos) {
        eventos.forEach(e -> System.out.println(e.getTitle() + " - " + e.getDateTime()));
    }

    private void confirmarPresenca() {
        listar(eventController.getUpcomingEvents());
        System.out.println("Digite o nome do evento para confirmar presença:");
        String nome = scanner.nextLine();
        for (Event e : eventController.getUpcomingEvents()) {
            if (e.getTitle().equalsIgnoreCase(nome)) {
                if (!user.getConfirmedEvents().contains(e)) {
                    user.addConfirmedEvent(e);
                    System.out.println("Presença confirmada!");
                } else {
                    System.out.println("Você já confirmou presença nesse evento.");
                }
                return;
            }
        }
        System.out.println("Evento não encontrado.");
    }

    private void listarPresencasConfirmadas() {
        System.out.println("Eventos confirmados:");
        user.getConfirmedEvents().forEach(e ->
                System.out.println(e.getTitle() + " - " + e.getDateTime())
        );
    }

    private void cancelarPresenca() {
        List<Event> eventos = user.getConfirmedEvents();
        listar(eventos);
        System.out.println("Digite o nome do evento para cancelar:");
        String nome = scanner.nextLine();
        eventos.removeIf(e -> e.getTitle().equalsIgnoreCase(nome));
        System.out.println("Presença cancelada.");
    }

    private void criarEvento() {
        System.out.println("Título:");
        String title = scanner.nextLine();
        System.out.println("Categoria:");
        String category = scanner.nextLine();
        System.out.println("Descrição:");
        String description = scanner.nextLine();
        System.out.println("Data e hora (ex: 2025-06-05T19:00):");
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());

        eventController.addEvent(new Event(title, category, description, dateTime));
        System.out.println("Evento criado!");
    }
}
