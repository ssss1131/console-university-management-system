package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.AuthService;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

public class LoginCommand implements Command {
    private final AuthService authService = new AuthService();
    private final Menu menu = Menu.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = menu.getLanguage();
        AuthView view = ViewFactory.getAuthView(currentLanguage);
        String email = view.getEmail();
        String password = view.getPassword();

        User user = authService.authenticate(email, password);

        if (user != null) {
            menu.setLoggedUser(user);
            view.displayLoginSuccess(user.getFirstName(), user.getLastName());
        } else {
            view.displayLoginFailure();
        }
    }
}
