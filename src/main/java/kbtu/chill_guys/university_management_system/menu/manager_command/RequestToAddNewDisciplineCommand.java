package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineStatusStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import static main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status.PENDING_APPROVAL;

public class RequestToAddNewDisciplineCommand implements Command {

    private final ManagerView view = new ManagerView();
    private final DisciplineStatusStorage disciplineStatusStorage = DisciplineStatusStorage.getInstance();

    @Override
    public void execute() {
        Discipline discipline = view.getNewDisciplineInput();
        disciplineStatusStorage.setStatus(discipline, PENDING_APPROVAL);
        view.showRequestSentConfirmation();

    }
}
