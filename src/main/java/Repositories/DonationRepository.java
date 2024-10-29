package Repositories;

import Models.Donation;
import Connection.OwnMySQLConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationRepository {

   


    public Donation addDonation(int projectId, int userId, double amount) {
        String insertQuery = "INSERT INTO Donations (project_id, user_id, amount) VALUES (?, ?, ?)";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, projectId);
            statement.setInt(2, userId);
            statement.setDouble(3, amount);
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return new Donation(generatedKeys.getInt(1), projectId, userId, amount, new Date(2024));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean deleteDonation(int donationId) {
        String deleteQuery = "DELETE FROM Donations WHERE donation_id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {

            statement.setInt(1, donationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateDonation(int donationId, int projectId, int userId, double amount) {
        String updateQuery = "UPDATE Donations SET project_id = ?, user_id = ?, amount = ? WHERE donation_id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateQuery)) {

            statement.setInt(1, projectId);
            statement.setInt(2, userId);
            statement.setDouble(3, amount);
            statement.setInt(4, donationId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Donation getDonationById(int donationId) {
        String selectQuery = "SELECT * FROM Donations WHERE donation_id = ?";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {

            statement.setInt(1, donationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Donation(
                        resultSet.getInt("donation_id"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("donation_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Donation> getAllDonations() {
        List<Donation> donationList = new ArrayList<>();
        String selectAllQuery = "SELECT * FROM Donations";
        try (Connection connection = OwnMySQLConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAllQuery);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                donationList.add(new Donation(
                        resultSet.getInt("donation_id"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getDouble("amount"),
                        resultSet.getTimestamp("donation_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return donationList;
    }
}
