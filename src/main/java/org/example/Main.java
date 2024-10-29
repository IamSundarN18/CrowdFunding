package org.example;

import Repositories.DonationRepository;
import Repositories.ProjectRepository;
import Repositories.UserRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        UserRepository userRepo = new UserRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        DonationRepository donationRepository = new DonationRepository();

        var ans = donationRepository.addDonation(2,3,1000);
        //var ans = projectRepository.addProject(1, "Cancer Patients", "Generating funds for geetha", 76000);
        System.out.println(ans);
    }
}