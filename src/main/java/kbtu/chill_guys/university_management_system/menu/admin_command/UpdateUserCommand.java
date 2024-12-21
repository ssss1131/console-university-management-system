package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;

import java.util.List;
import java.util.Map;

public class UpdateUserCommand implements Command {
    private final AdminService adminService = new AdminService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        AdminView view = ViewFactory.getAdminView(currentLanguage);

        List<User> users = adminService.getAllUsers();
        if (users.isEmpty()) {
            view.displayNoUsersForUpdate();
            return;
        }

        view.displayAllUsers(users);

        int userIndex = view.getUserIndexForDeletion(users.size());
        User selectedUser = users.get(userIndex);

        Map<String, Object> updatedFields = view.getFieldsForUpdate(selectedUser);

        for (Map.Entry<String, Object> entry : updatedFields.entrySet()) {
            switch (entry.getKey()) {
                case "firstName" -> selectedUser.setFirstName((String) entry.getValue());
                case "lastName" -> selectedUser.setLastName((String) entry.getValue());
                case "email" -> selectedUser.setEmail((String) entry.getValue());
            }
        }

        adminService.modifyUser(selectedUser);

        view.displayUserUpdatedSuccessfully();
    }
}
