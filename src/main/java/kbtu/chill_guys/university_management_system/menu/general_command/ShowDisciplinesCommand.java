package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.view.GeneralView;

import java.util.List;

public class ShowDisciplinesCommand implements Command {

    private final DisciplineService service = new DisciplineService();
    private final GeneralView view = new GeneralView();

    @Override
    public void execute() {
        List<School> schools = List.of(School.values());

        School selectedSchool = view.showSchoolSelection(schools);

        List<Discipline> disciplines;
        if (selectedSchool == null) {
            disciplines = service.getAllDisciplines();
        } else {
            disciplines = service.getDisciplinesBySchool(selectedSchool);
        }

        view.displayDisciplines(disciplines);
    }
}
