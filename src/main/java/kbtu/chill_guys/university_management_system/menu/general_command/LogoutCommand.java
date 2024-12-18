package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.view.AuthView;

public class LogoutCommand implements Command {
    private final Menu menu = Menu.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = menu.getLanguage();
        AuthView view = ViewFactory.getAuthView(currentLanguage);
        if (menu.getLoggedUser() == null) {
            view.displayLogoutMessage(false);
        } else {
            menu.setLoggedUser(null);
            view.displayLogoutMessage(true);
        }
    }

}
