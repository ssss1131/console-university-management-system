package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineRegistrationStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

public class CloseRegistrationCommand implements Command {
    private final DisciplineRegistrationStorage storage = DisciplineRegistrationStorage.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        Semester semester = storage.closeRegistration();
        view.showSuccessClosingRegistration(semester);
    }
}
