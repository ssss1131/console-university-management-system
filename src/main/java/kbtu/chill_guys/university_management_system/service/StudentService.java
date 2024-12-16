package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.DisciplineRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineRegistrationStorage;

import java.util.*;

public class StudentService {

    private final DisciplineRepository disciplineRepository = new DisciplineRepository();
    private final Student student = (Student) Menu.getInstance().getLoggedUser();


    public boolean isYearValid(Student student, String selectedYear) {
        int startYear = student.getEnrollmentDate().getYear();
        int endYear = startYear + student.getStudyDuration();

        for (int year = startYear; year < endYear; year++) {
            if ((year + "-" + (year + 1)).equals(selectedYear)) {
                return true;
            }
        }
        return false;
    }

//    public boolean isPeriodValid(Student student, String selectedYear, Period selectedPeriod) {
//        return student.getSemesterDisciplines().keySet().stream()
//                .anyMatch(semester ->
//                        (semester.getYear() + "-" + semester.getYearEnd()).equals(selectedYear)
//                                && semester.getPeriod() == selectedPeriod);
//    }


    public List<String> getDisciplineByYearAndPeriod(Student student, String year, Period period) {
        return disciplineRepository.findCoursesByYearAndPeriod(year, period);
    }
    
    
}
