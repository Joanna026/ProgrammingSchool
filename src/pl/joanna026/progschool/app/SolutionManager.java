package pl.joanna026.progschool.app;

import pl.joanna026.progschool.dao.ExerciseDAO;
import pl.joanna026.progschool.dao.SolutionDAO;
import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.Exercise;
import pl.joanna026.progschool.model.Solution;
import pl.joanna026.progschool.model.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

public class SolutionManager {

    public static void main(String[] args) {
        String useCase;
        do {
            useCase = option();
            switch (useCase) {
                case "add":
                    addCase();
                    break;
                case "view":
                    viewCase();
                    break;
            }
        } while (!useCase.equals("quit"));
    }

    private static void addCase() {
        Scanner scanner = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        User[] usersList = userDAO.findAll();
        for (User user : usersList) {
            System.out.println(user);
        }
        System.out.println("\n Podaj ID użytkownika, któremu chcesz przypisać rozwiązanie: ");
        int userId = scanner.nextInt();

        ExerciseDAO exerciseDAO = new ExerciseDAO();
        Exercise[] exerciseList = exerciseDAO.findAll();
        for (Exercise exercise : exerciseList) {
            System.out.println(exercise);
        }
        System.out.println("\n Podaj ID zadania, którego rozwiązanie chcesz przypisać: ");
        int exerciseId = scanner.nextInt();

        SolutionDAO solutionDAO = new SolutionDAO();
        Date date = new Date();
        solutionDAO.create(new Solution(new Timestamp(date.getTime()), null, null, exerciseId, userId));

        System.out.println("Dodano rozwiązanie");
    }

    private static void viewCase() {
        Scanner scanner = new Scanner(System.in);

        UserDAO userDAO = new UserDAO();
        User[] usersList = userDAO.findAll();
        for (User user : usersList) {
            System.out.println(user);
        }
        System.out.println("\n Podaj ID użytkownika, którego rozwiązania chcesz zobaczyć: ");
        int userId = scanner.nextInt();

        SolutionDAO solutionDAO = new SolutionDAO();
        Solution[] solutionsList = solutionDAO.findAllByUserId(userId);
        for (Solution solution : solutionsList) {
            System.out.println(solution);
        }
    }

    private static String option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "- add – przypisanie zadania do użytkownika,\n" +
                "- view - przeglądanie rozwiązań wybranego użytkownika,\n" +
                "- quit – powrót do menu głównego.");

        return scanner.nextLine();
    }

}
