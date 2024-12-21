package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;

import java.util.List;

public class DeleteUserCommand implements Command {
    private final AdminService adminService = new AdminService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        AdminView view = ViewFactory.getAdminView(currentLanguage);

        List<User> users = adminService.getAllUsers();
        if (users.isEmpty()) {
            view.displayNoUsersToDelete();
            return;
        }

        view.displayAllUsers(users);

        int userIndex = view.getUserIndexForDeletion(users.size());
        User selectedUser = users.get(userIndex);

        boolean confirm = view.confirmDeletion(selectedUser);
        if (!confirm) {
            view.displayUserDeletionCancelled();
            return;
        }

        adminService.removeUser(selectedUser.getId());
        view.displayUserDeletedSuccessfully();
    }
}
