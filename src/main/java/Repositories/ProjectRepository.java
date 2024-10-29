package Repositories;

import Models.Project;
import Connection.OwnMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    

    // Add a new project
    public Project addProject(int userId, String title, String description, double goalAmount) {
        String insertQuery = "INSERT INTO Projects (user_id, title, description, goal_amount) VALUES (?, ?, ?, ?)";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, userId);
            statement.setString(2, title);
            statement.setString(3, description);
            statement.setDouble(4, goalAmount);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new Project(generatedKeys.getInt(1), userId, title, description, goalAmount, 0.00, "active", new Date(2024));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete a project by ID
    public boolean deleteProject(int projectId) {
        String deleteQuery = "DELETE FROM Projects WHERE project_id = ?";
        try (Connection connection = 
OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, projectId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update a project by ID
    public boolean updateProject(int projectId, String title, String description, double goalAmount, String status) {
        String updateQuery = "UPDATE Projects SET title = ?, description = ?, goal_amount = ?, status = ? WHERE project_id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, title);
            statement.setString(2, description);
            statement.setDouble(3, goalAmount);
            statement.setString(4, status);
            statement.setInt(5, projectId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get a project by ID
    public Project getProjectById(int projectId) {
        String selectQuery = "SELECT * FROM Projects WHERE project_id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setInt(1, projectId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Project(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("goal_amount"),
                        resultSet.getDouble("amount_raised"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("created_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all projects
    public List<Project> getAllProjects() {
        List<Project> projectList = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM Projects";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                projectList.add(new Project(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDouble("goal_amount"),
                        resultSet.getDouble("amount_raised"),
                        resultSet.getString("status"),
                        resultSet.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }
}
