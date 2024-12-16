package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Program;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.StudentRole;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.exception.CreditsOverflowException;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.DisciplineRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineRegistrationStorage;
import main.java.kbtu.chill_guys.university_management_system.storage.StudentDisciplineStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DisciplineRegistrationService {

    private final DisciplineRegistrationStorage registrationStorage = DisciplineRegistrationStorage.getInstance();
    private final DisciplineRepository disciplineRepository = new DisciplineRepository();
    private final StudentDisciplineStorage disciplineStorage = StudentDisciplineStorage.getInstance();

    public List<Discipline> getAvailableDisciplines(Integer course, Semester semester, StudentRole role, Program program) {

        List<Discipline> alreadyExistingDisciplines = registrationStorage.getAvailableDisciplinesForStudent(course, role, program);

        return disciplineRepository.getAllLines().stream()
                .filter(discipline -> discipline.getSemester().equals(semester))
                .filter(discipline -> discipline.getTargetAudience() == role)
                .filter(discipline -> discipline.getTargetSpecializations().contains(program))
                .filter(discipline -> !alreadyExistingDisciplines.contains(discipline))
                .toList();

    }

    public List<Discipline> getDisciplinesForStudentToRegister(Student student, Semester semester) {
        int studentCourse = calculateStudentCourse(student, semester);

        return DisciplineRegistrationStorage.getInstance()
                .getAvailableDisciplinesForStudent(
                        studentCourse,
                        student.getStudentRole(),
                        student.getProgram()
                )
                .stream()
                .filter(discipline -> !registrationStorage.getStudentsForDiscipline(discipline).contains(student))
                .toList();

    }


    public void openRegistration(int course, StudentRole role, Semester semester, List<Discipline> disciplines, Program program) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("No disciplines available to register for Course " + course + " (" + role + ").");
            return;
        }
        if (semester != registrationStorage.getRegisteringSemester()) {
            registrationStorage.resetForNewSemester(semester);
        }
        registrationStorage.assignDisciplinesToCourseAndProgram(course, role, program, disciplines);

        System.out.println("Registration successfully opened for Course " + course + " (" + role + ", " + program + ").");

    }

    public void registerStudentToDisciplines(Student student, List<Discipline> disciplines, Semester semester) {
        int totalCredits = 0;
        List<Discipline> disciplineList = new ArrayList<>(disciplines);
        disciplineList.addAll(disciplineStorage.getDisciplines(student, semester));
        for (Discipline discipline : disciplineList) {
            totalCredits += discipline.getCredits();
            if (totalCredits > 22) {
                throw new CreditsOverflowException("Registration failed: Total credits cannot exceed 22.");
            }
        }

        for (Discipline discipline : disciplines) {
            registrationStorage.registerStudentToDiscipline(discipline, student);
            disciplineStorage.addDiscipline(student, semester, discipline);
        }

        student.setCredits(student.getCredits() + totalCredits);
        System.out.println("Student " + student.getFirstName() + " successfully registered to selected disciplines.");
    }


    public boolean haveOpenedRegistration() {
        return registrationStorage.getRegisteringSemester() != null;
    }

    public Semester getSemester() {
        return registrationStorage.getRegisteringSemester();
    }

    public int calculateStudentCourse(Student student, Semester semester) {
        LocalDate enrollmentDate = student.getEnrollmentDate();
        int enrollmentYear = enrollmentDate.getYear();

        int yearDifference = semester.getYear() - enrollmentYear;

        if (semester.getPeriod() == Period.FALL) {
            return yearDifference + 1;
        } else {
            return yearDifference;
        }
    }

}

