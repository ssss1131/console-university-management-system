package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineRegistrationStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;
import java.util.Map;

public class GetInfoAboutRegistrationCommand implements Command {

    DisciplineRegistrationStorage storage = DisciplineRegistrationStorage.getInstance();
    ManagerView view = new ManagerView();

    @Override
    public void execute() {
        Map<Integer, Map<StudentRole, Map<Program, List<Discipline>>>> courseDisciplineMapping = storage.getCourseDisciplineMapping();

        view.showRegistrationInfo(courseDisciplineMapping, storage.getRegisteringSemester());
    }
}
