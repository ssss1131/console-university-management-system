package main.java.kbtu.chill_guys.university_management_system.menu.general_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.view.GeneralView;

import java.util.List;

public class ShowDisciplinesCommand implements Command {
    private final DisciplineService service = new DisciplineService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        GeneralView view = ViewFactory.getGeneralView(currentLanguage);

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
