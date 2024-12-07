package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.service.AuthService;

public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public boolean login(String email, String password) {
        return authService.authenticate(email, password);
    }
}
