package pl.joanna026.progschool.app;

import pl.joanna026.progschool.dao.ExerciseDAO;
import pl.joanna026.progschool.dao.SolutionDAO;
import pl.joanna026.progschool.model.Exercise;
import pl.joanna026.progschool.model.Solution;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class SolutionCreator {

    public static void main(String[] args) {

        int userId = Integer.parseInt(args[0]);

        String useCase;
        do {
            useCase = option();
            switch (useCase) {
                case "add":
                    addCase(userId);
                    break;
                case "view":
                    viewCase(userId);
                    break;
            }
        } while (!useCase.equals("quit"));
    }

    private static void viewCase(int userId) {
        SolutionDAO solutionDAO = new SolutionDAO();
        Solution[] solutionsList = solutionDAO.findAllByUserId(userId);
        System.out.println("Twoje rozwiązania:");
        for (Solution solution : solutionsList) {
            System.out.println(solution);
        }
    }

    private static void addCase(int userId) {
        SolutionDAO solutionDAO = new SolutionDAO();
        Solution[] solutionsList = solutionDAO.findAllByUserId(userId);
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        Exercise[] allExercises = exerciseDAO.findAll();
        int[] noSolution = new int[0];

        for (Exercise exercise : allExercises) {
            boolean exists = false;
            for (Solution solution : solutionsList) {
                if (exercise.getId() == solution.getExercise_id()) {
                    exists = true;
                }
            }
            if (!exists) {
                System.out.println(exercise);
                noSolution = Arrays.copyOf(noSolution, noSolution.length + 1);
                noSolution[noSolution.length - 1] = exercise.getId();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id zadania, do którego chcesz dodać rozwiązanie:");
        int exerciseId = scanner.nextInt();
        boolean allowed = false;
        for (int id : noSolution) {
            if (id == exerciseId) {
                allowed = true;
            }
        }
        if (allowed) {
            System.out.println("Podaj treść rozwiązania:");
            scanner.nextLine();
            String solutionDescription = scanner.nextLine();
            Date date = new Date();
            solutionDAO.create(new Solution(new Timestamp(date.getTime()), null, solutionDescription, exerciseId, userId));
            System.out.println("Dodano rozwiązanie.");
        } else {
            System.out.println("Rozwiązanie tego zadania już istnieje.");
        }
    }

    private static String option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "- add – dodawanie rozwiązania,\n" +
                "- view – przeglądanie rozwiązań,\n" +
                "- quit – powrót do menu głównego.");

        return scanner.nextLine();
    }
}
