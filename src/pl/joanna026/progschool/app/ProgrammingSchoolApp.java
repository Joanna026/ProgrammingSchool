package pl.joanna026.progschool.app;



import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.Group;
import pl.joanna026.progschool.model.User;
import pl.joanna026.progschool.util.DBUtil;


import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class ProgrammingSchoolApp {

    public static void main(String[] args) {
        int useCase;
        do {
            useCase=option();
            switch (useCase){
                case 1:
                    UsersManager usersManager = new UsersManager();
                    usersManager.main(null);
                    break;
                case 2:
                    GroupManager groupManager = new GroupManager();
                    groupManager.main(null);
                    break;
                case 3:
                    SolutionCreator solutionCreator = new SolutionCreator();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Podaj swój numer id: ");
                    int userId = scanner.nextInt();
                    scanner.nextLine();
                    String[] id = {userId+""};
                    solutionCreator.main(id);
                    break;
                case 4:
                    SolutionManager solutionManager = new SolutionManager();
                    solutionManager.main(null);
                    break;
                case 5:
                    ExerciseManager exerciseManager = new ExerciseManager();
                    exerciseManager.main(null);
                    break;
            }
        } while (useCase!=0);
    }

    private static int option() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wybierz, co chcesz zrobić:\n"+
                "- 1 - zarządzaj użytkownikami,\n"+
                "- 2 - zarządzaj grupami,\n"+
                "- 3 - dodaj rozwiązanie zadania,\n"+
                "- 4 - zarządzaj rozwiązaniami,\n"+
                "- 5 - zarządzaj zadaniami,\n"+
                "- 0 - zakończenie programu.");

        int useCase=scanner.nextInt();
        scanner.nextLine();
        return useCase;
    }
}