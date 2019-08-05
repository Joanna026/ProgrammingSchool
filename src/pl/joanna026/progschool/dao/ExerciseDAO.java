package pl.joanna026.progschool.dao;

import pl.joanna026.progschool.model.Exercise;

import pl.joanna026.progschool.util.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class ExerciseDAO {
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise (title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title=?,description=? WHERE id=?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id=?";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise";


    public Exercise create(Exercise exercise) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);

            //Ustawienie parametrów zapytania (wartości do wstawienia)
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());

            //Wykonanie zapytania
            statement.executeUpdate();

            // Pobranie zestawu wygenerowanych kluczy
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.first()) {
                int generatedKey = generatedKeys.getInt(1);
                exercise.setId(generatedKey);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercise;
    }

    public Exercise read(int id) {

        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static void update(Exercise exercise) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2,exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delete(int id) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] findAll() {
        try (Connection conn = DBUtil.connect()) {
            Exercise[] exercises = new Exercise[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private Exercise[] addToArray(Exercise e, Exercise[] exercises) {
        Exercise[] tmpExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tmpExercises[exercises.length] = e;
        return tmpExercises;
    }

}


