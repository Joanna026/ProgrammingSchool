package pl.joanna026.progschool.dao;

public class ExerciseDAO {
    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise (title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercise WHERE id = ?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title=? WHERE id=?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id=?";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise";
}


