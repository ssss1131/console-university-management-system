package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.util.EmailValidator;
import main.java.kbtu.chill_guys.university_management_system.util.PasswordUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;

import java.util.Map;

public class CreateUserCommand implements Command {
    private final AdminService adminService = new AdminService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        AdminView view = ViewFactory.getAdminView(currentLanguage);

        EmailValidator emailValidator = new EmailValidator(adminService);

        String email = emailValidator.validateUniqueEmail();

        Map<String, Object> data = view.getUserInput();
        data.put("email", email);

        String password = (String) data.get("password");
        String salt = PasswordUtil.generateSalt();
        String hashedPassword = PasswordUtil.hashPassword(password, salt);

        data.put("password", hashedPassword);
        data.put("salt", salt);

        User user = adminService.createUser(data);

        view.displayUserCreated(user);
    }
}
