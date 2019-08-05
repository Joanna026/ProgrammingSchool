package pl.joanna026.progschool.app;

import pl.joanna026.progschool.dao.ExerciseDAO;
import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.Exercise;
import pl.joanna026.progschool.model.User;

import java.util.Scanner;

public class ExerciseManager {

    public static void main(String[] args) {
        String useCase;
        do {
            useCase=option();
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
        Exercise exercise = newExercise();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        exerciseDAO.create(exercise);
    }

    private static void editCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id zadania, którego dane chcesz zmienić: ");
        int id = scanner.nextInt();

        Exercise exercise=newExercise();
        exercise.setId(id);
        ExerciseDAO exerciseDAO=new ExerciseDAO();
        ExerciseDAO.update(exercise);
    }

    private static void deleteCase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer id zadania, które chcesz usunąć: ");
        int id = scanner.nextInt();

        ExerciseDAO exerciseDAO=new ExerciseDAO();
        ExerciseDAO.delete(id);
    }

    private static String option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz jedną z opcji:\n" +
                "- add – dodanie zadania,\n" +
                "- edit – edycja zadania,\n" +
                "- delete – usunięcie zadania,\n" +
                "- quit – zakończenie programu.");

        return scanner.nextLine();
    }

    private static Exercise newExercise() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj tytuł zadania: ");
        String title = scanner.nextLine();
        System.out.println("Podaj opis zadania: ");
        String description = scanner.nextLine();

        Exercise exercise=new Exercise(title, description);
        return exercise;
    }
}
