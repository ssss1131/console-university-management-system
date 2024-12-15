package main.java.kbtu.chill_guys.university_management_system.menu.dean_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineStatusStorage;
import main.java.kbtu.chill_guys.university_management_system.view.DeanView;

import java.util.Map;
import java.util.Vector;

public class ApproveNewDisciplineCommand implements Command {

    private final DisciplineStatusStorage disciplineStatusStorage = DisciplineStatusStorage.getInstance();
    private final DeanView deanView = new DeanView();

    @Override
    public void execute() {
        Vector<Discipline> allPendingDisciplines = disciplineStatusStorage.getDisciplinesByStatus(Status.PENDING_APPROVAL);
        Map<Discipline, Status> status = deanView.approveOrDeclineDisciplines(allPendingDisciplines);
        status.forEach(disciplineStatusStorage::setStatus);
    }
}
