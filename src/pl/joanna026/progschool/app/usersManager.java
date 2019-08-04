package pl.joanna026.progschool.app;

import org.graalvm.compiler.lir.LIRInstruction;
import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.User;

import java.util.Scanner;

public class usersManager {

    public static void main(String[] args) {

        switch (option()) {
            case "add":
                addCase();
            case "edit":
                editCase();
                break;
            case "delete":
                deleteCase();
                break;
            case "quit":
                break;
        }
    }

    private static void addCase() {
        User user = newUser();
        UserDAO userDAO = new UserDAO();
        userDAO.create(user);
    }

    private static void editCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id użytkownika, którego dane chcesz zmienić: ");
        int id = scanner.nextInt();

        User user = newUser();
        user.setId(id);
        UserDAO userDAO = new UserDAO();
        userDAO.update(user);
    }

    private static void deleteCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id użytkownika, którego chcesz usunąć: ");
        int id = scanner.nextInt();

        UserDAO userDAO = new UserDAO();
        userDAO.delete(id);
    }

    private static String option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "- add – dodanie użytkownika,\n" +
                "- edit – edycja użytkownika,\n" +
                "- delete – usunięcie użytkownika,\n" +
                "- quit – zakończenie programu.");

        return scanner.nextLine();
    }


    private static User newUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj adres email: ");
        String email = scanner.nextLine();
        System.out.println("Podaj nazwę użytkownika: ");
        String username = scanner.nextLine();
        System.out.println("Podaj hasło: ");
        String password = scanner.nextLine();
        System.out.println("Podaj numer id grupy: ");
        int group_id = scanner.nextInt();

        User user = new User(email, username, password, group_id);
        return user;
    }
}
