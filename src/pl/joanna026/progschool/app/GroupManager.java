package pl.joanna026.progschool.app;

import pl.joanna026.progschool.dao.GroupDAO;
import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.Group;
import pl.joanna026.progschool.model.User;

import java.util.Scanner;

public class GroupManager {

    public static void main(String[] args) {
        String useCase;
        do {
            useCase = option();
            switch (useCase) {
                case "add":
                    addCase();
                    break;
                case "edit":
                    editCase();
                    break;
                case "delete":
                    deleteCase();
                    break;
            }
        } while (!useCase.equals("quit"));
    }

    private static void addCase() {
        Group group = newGroup();
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.create(group);
    }

    private static void editCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id grupy, której dane chcesz zmienić: ");
        int id = scanner.nextInt();

        Group group = newGroup();
        group.setId(id);
        GroupDAO groupDAO = new GroupDAO();
        groupDAO.update(group);
    }

    private static void deleteCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id grupy, którą chcesz usunąć: ");
        int id = scanner.nextInt();

        GroupDAO groupDAO = new GroupDAO();
        groupDAO.delete(id);
    }

    private static String option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "- add – dodanie grupy,\n" +
                "- edit – edycja grupy,\n" +
                "- delete – usunięcie grupy,\n" +
                "- quit – zakończenie programu.");

        return scanner.nextLine();
    }


    private static Group newGroup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj nazwę grupy: ");
        String name = scanner.nextLine();
        Group group = new Group(name);

        return group;
    }

}
