package pl.joanna026.progschool.dao;

import pl.joanna026.progschool.model.Group;
import pl.joanna026.progschool.util.DBUtil;

import java.sql.*;
import java.util.Arrays;

public class GroupDAO {
    private static final String CREATE_GROUP_QUERY = "INSERT INTO user_group (name) VALUES (?)";
    private static final String READ_GROUP_QUERY = "SELECT * FROM user_group WHERE id = ?";
    private static final String UPDATE_GROUP_QUERY = "UPDATE user_group SET name=? WHERE id=?";
    private static final String DELETE_GROUP_QUERY = "DELETE FROM user_group WHERE id=?";
    private static final String FIND_ALL_GROUP_QUERY = "SELECT * FROM user_group";


    public Group create(Group group) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);

            //Ustawienie parametrów zapytania (wartości do wstawienia)
            statement.setString(1, group.getName());

            //Wykonanie zapytania
            statement.executeUpdate();

            // Pobranie zestawu wygenerowanych kluczy
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.first()) {
                int generatedKey = generatedKeys.getInt(1);
                group.setId(generatedKey);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return group;
    }

    public Group read(int id) {

        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void update(Group group) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void delete(int id) {
        try (Connection conn = DBUtil.connect()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group[] findAll() {
        try (Connection conn = DBUtil.connect()) {
            Group[] groups = new Group[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                groups = addToArray(group, groups);
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Group[] addToArray(Group g, Group[] groups) {
        Group[] tmpGroups = Arrays.copyOf(groups, groups.length + 1);
        tmpGroups[groups.length] = g;
        return tmpGroups;
    }
}


