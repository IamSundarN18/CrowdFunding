package Models;

import java.sql.Date;
import java.sql.Timestamp;

public class Project {
    private int id;
    private int userId;
    private String title;
    private String description;
    private double goalAmount;
    private double amountRaised;
    private String status;

    public Project(int id, int userId, String title, String description, double goalAmount, double amountRaised, String status) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.goalAmount = goalAmount;
        this.amountRaised = amountRaised;
        this.status = status;
    }

    public Project(int id, String name, String description, double targetAmount, int userId) {
    }

    public Project(int projectId, int userId, String title, String description, double goalAmount, double amountRaised, String status, Timestamp createdAt) {
    }

    public Project(int anInt, int userId, String title, String description, double goalAmount, double amountRaised, String active, Date date) {
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getGoalAmount() { return goalAmount; }
    public void setGoalAmount(double goalAmount) { this.goalAmount = goalAmount; }

    public double getAmountRaised() { return amountRaised; }
    public void setAmountRaised(double amountRaised) { this.amountRaised = amountRaised; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
