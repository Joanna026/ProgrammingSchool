package pl.joanna026.progschool.app;



import pl.joanna026.progschool.dao.UserDAO;
import pl.joanna026.progschool.model.User;
import pl.joanna026.progschool.util.DBUtil;


import java.sql.SQLException;
import java.util.Arrays;

public class TestApp {

    public static void main(String[] args) {

//        testConnect();
//        testCreateUser();
//        testReadUser();
//        testUpdateUser();
//        testDeleteUser();

        testFindAll();


    }

    private static void testFindAll() {
        UserDAO userDAO= new UserDAO();
        User user1= new User("test1", "test1", "test1");
        User user2= new User("test2", "test2", "test2");
        User user3= new User("test3", "test3", "test3");


        userDAO.create(user1);
        userDAO.create(user2);
        userDAO.create(user3);

        User[] allUsers =userDAO.findAll();
        System.out.println(Arrays.toString(allUsers));
    }

    private static void testDeleteUser() {
        UserDAO userDAO= new UserDAO();
        User user=userDAO.read(3);

        userDAO.delete(user.getId());

        User userAfterDelete=userDAO.read(user.getId());
        System.out.println(userAfterDelete);
    }

    private static void testUpdateUser() {
        UserDAO userDAO= new UserDAO();
        User user=userDAO.read(3);
        user.setUsername("newusername");

        userDAO.update(user);

        User userAfterUpdate=userDAO.read(3);
        System.out.println(userAfterUpdate);
    }

    private static void testReadUser() {
        UserDAO userDAO=new UserDAO();
        User user=userDAO.read(3);
        System.out.println(user);

        User user2 =userDAO.read(101);
        System.out.println(user2);
    }

    private static void testConnect() throws SQLException {
        DBUtil.connect();
    }

    private static void testCreateUser(){
        User user = new User("test1@test.pl", "test", "test");
        System.out.println(user);

        UserDAO userDAO = new UserDAO();
        user = userDAO.create(user);

        System.out.println(user);
    }

}

//zadanie 5 - do klasy SolutionDAO
//każdy program w osobnej klasie zad 1 i 4
//zadanie 2 z konsoli
//wiele do wielu oceny, jeden do wielu ocena