package Models;

import java.util.Date;

public class Donation {
    private int id;
    private int projectId;
    private int userId;
    private double amount;
    private Date donationDate;

    public Donation(int id, int projectId, int userId, double amount, Date donationDate) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.amount = amount;
        this.donationDate = donationDate;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDonationDate() { return donationDate; }
    public void setDonationDate(Date donationDate) { this.donationDate = donationDate; }
}
