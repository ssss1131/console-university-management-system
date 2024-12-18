package main.java.kbtu.chill_guys.university_management_system.menu.student_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.exception.CreditsOverflowException;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineRegistrationService;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.List;

public class RegisterToSemesterCommand implements Command {
    private final DisciplineRegistrationService disciplineRegistrationService = new DisciplineRegistrationService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        StudentView view = ViewFactory.getStudentView(currentLanguage);

        Student student = (Student) Menu.getInstance().getLoggedUser();

        boolean haveOpenedRegistration = disciplineRegistrationService.haveOpenedRegistration();
        if (!haveOpenedRegistration) {
            view.showMessage("Registration is closed!");
            return;
        }

        Semester semester = disciplineRegistrationService.getSemester();

        List<Discipline> availableDisciplines = disciplineRegistrationService.getDisciplinesForStudentToRegister(
                student, semester);

        if (availableDisciplines==null || availableDisciplines.isEmpty()) {
            view.showMessage("No available disciplines for registration.");
            return;
        }
        view.displayAvailableDisciplines(availableDisciplines);

        List<Discipline> selectedDisciplines = view.selectDisciplinesForRegistration(availableDisciplines);

        try {
            disciplineRegistrationService.registerStudentToDisciplines(student, selectedDisciplines, semester);
            view.displayRegistrationConfirmation(selectedDisciplines);
        } catch (CreditsOverflowException e) {
            System.out.println(e.getMessage());
        }
    }
}

