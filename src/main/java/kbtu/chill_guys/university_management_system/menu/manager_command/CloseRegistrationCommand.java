package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineRegistrationStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

public class CloseRegistrationCommand implements Command {

    private final DisciplineRegistrationStorage storage = DisciplineRegistrationStorage.getInstance();
    private final ManagerView view = new ManagerView();

    @Override
    public void execute() {
        Semester semester = storage.closeRegistration();
        view.showSuccessClosingRegistration(semester);
    }
}
