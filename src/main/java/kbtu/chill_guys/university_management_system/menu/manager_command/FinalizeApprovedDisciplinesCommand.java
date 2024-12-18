package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.repository.DisciplineRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineStatusStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;

public class FinalizeApprovedDisciplinesCommand implements Command {
    private final DisciplineStatusStorage disciplineStatusStorage = DisciplineStatusStorage.getInstance();
    private final DisciplineRepository disciplineRepository = new DisciplineRepository();
    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        List<Discipline> approvedDisciplines = disciplineStatusStorage.getDisciplinesByStatus(Status.ASSIGNED);
        List<Discipline> declinedDisciplines = disciplineStatusStorage.getDisciplinesByStatus(Status.CANCELLED);

        view.displayDisciplinesByStatus(approvedDisciplines, declinedDisciplines);

        if (!approvedDisciplines.isEmpty()) {
            List<Discipline> selectedDisciplines = view.selectDisciplinesToFinalize(approvedDisciplines);
            if(!selectedDisciplines.isEmpty()){
                for (Discipline discipline : selectedDisciplines) {
                    disciplineRepository.save(discipline);
                    disciplineStatusStorage.removeDiscipline(discipline);
                }
                System.out.println("Selected disciplines have been added to the database.");
            }
        } else {
            System.out.println("\nNo approved disciplines to finalize.");
        }
        System.out.println("-----------------------------------------------------");

    }
}
