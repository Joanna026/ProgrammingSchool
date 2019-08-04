package pl.joanna026.progschool.model;

import java.text.SimpleDateFormat;
import java.util.Objects;

public class Solution {

    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int users_id;


    public Solution() {
    }

    public Solution(String created, String updated,
                    String description, int exercise_id, int users_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String  getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", users_id=" + users_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution = (Solution) o;
        return id == solution.id &&
                exercise_id == solution.exercise_id &&
                users_id == solution.users_id &&
                Objects.equals(created, solution.created) &&
                Objects.equals(updated, solution.updated) &&
                Objects.equals(description, solution.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created, updated, description, exercise_id, users_id);
    }
}
