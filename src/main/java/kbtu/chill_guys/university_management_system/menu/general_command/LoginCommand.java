package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AuthController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

public class LoginCommand implements Command {
    private final AuthController authController = new AuthController();
    private final AuthView authView = new AuthView();
    private final Menu menu = Menu.getInstance();



    @Override
    public void execute() {
        String email = authView.getEmail();
        String password = authView.getPassword();

        User user = authController.login(email, password);

        if (user != null) {
            menu.setLoggedUser(user);
            authView.displayLoginSuccess(user.getFirstName(), user.getLastName());
        } else {
            authView.displayLoginFailure();
        }
    }
}
