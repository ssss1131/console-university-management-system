package main.java.kbtu.chill_guys.university_management_system.menu.dean_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineStatusStorage;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.Map;
import java.util.Vector;

public class ApproveNewDisciplineCommand implements Command {
    private final DisciplineStatusStorage disciplineStatusStorage = DisciplineStatusStorage.getInstance();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        DeanView view = ViewFactory.getDeanView(currentLanguage);

        Vector<Discipline> allPendingDisciplines = disciplineStatusStorage.getDisciplinesByStatus(Status.PENDING_APPROVAL);
        Map<Discipline, Status> status = view.approveOrDeclineDisciplines(allPendingDisciplines);
        status.forEach(disciplineStatusStorage::setStatus);
    }
}
