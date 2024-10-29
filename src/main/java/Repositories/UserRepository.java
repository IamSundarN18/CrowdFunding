package Repositories;

import Models.User;
import Connection.OwnMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    public User addUser(String username, String email, String password) {
        String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new User(generatedKeys.getInt(1), username, email, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Delete a user by ID
    public boolean deleteUser(int id) {
        String deleteQuery = "DELETE FROM users WHERE id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update a user by ID
    public boolean updateUser(int id, String username, String email, String password) {
        String updateQuery = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setInt(4, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get a user by ID
    public User getUserById(int id) {
        String selectQuery = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all users
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM users";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                userList.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
