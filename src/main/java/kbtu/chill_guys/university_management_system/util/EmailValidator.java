package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.service.AdminService;

import java.util.Scanner;

public class EmailValidator {
    private final AdminService adminService;

    public EmailValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    public String validateUniqueEmail() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter email:");
            String email = InputValidatorUtil.validateEmailInput("Invalid email format. Please try again.");
            if (adminService.isEmailExists(email)) {
                System.out.println("The email is already in use. Please enter a different email.");
            } else {
                return email;
            }
        }
    }
}
