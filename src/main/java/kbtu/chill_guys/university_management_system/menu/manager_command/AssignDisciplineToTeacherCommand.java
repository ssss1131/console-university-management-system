package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;

public class AssignDisciplineToTeacherCommand implements Command {
    private final DisciplineService disciplineService = new DisciplineService();
    private final TeacherService teacherService = new TeacherService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);

        List<Discipline> availableDisciplines = disciplineService.getAllDisciplines();
        if (availableDisciplines.isEmpty()) {
            view.showNoDisciplinesAvailableMessage();
            return;
        }

        Discipline selectedDiscipline = view.selectDiscipline(availableDisciplines);

        List<Teacher> teachers = teacherService.getAllTeachers();
        if (teachers.isEmpty()) {
            view.showNoTeachersAvailableMessage();
            return;
        }

        Teacher selectedTeacher = view.selectTeacher(teachers);

        teacherService.assignDisciplineToTeacher(selectedTeacher, selectedDiscipline);

        view.showDisciplineAssignedMessage(selectedDiscipline, selectedTeacher);
    }
}
