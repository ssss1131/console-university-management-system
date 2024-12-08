package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

public class LoginCommand implements Command {
    private final AuthController loginController;
    private final AuthView authView;

    public LoginCommand(AuthController loginController, AuthView authView) {
        this.loginController = loginController;
        this.authView = authView;
    }

    @Override
    public void execute() {
        String email = authView.getEmail();
        String password = authView.getPassword();

        boolean isAuthenticated = loginController.login(email, password);

        if (isAuthenticated) {
            authView.displayLoginSuccess();
        } else {
            authView.displayLoginFailure();
        }
    }
}
