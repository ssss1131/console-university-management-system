package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.DisciplineRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {
    private final DisciplineRepository disiplineRepository = new DisciplineRepository();


    public Vector<Transcript> showTranscript() {
        //TODO
        return null;
    }

    public Vector<Organization> showOrganizations() {
        //TODO
        return null;
    }

    public Vector<Teacher> showTeachers() {
        //TODO
        return null;
    }

    public Map<LocalDate, Boolean> showAttendace() {
        //TODO
        return null;
    }

    public int showAcademicStanding() {
        //TODO
        return 0;
    }


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
//                        (semester.getYearStart() + "-" + semester.getYearEnd()).equals(selectedYear)
//                                && semester.getPeriod() == selectedPeriod);
//    }


    public List<String> getDisciplineByYearAndPeriod(Student student, String year, Period period) {
        return disiplineRepository.findCoursesByYearAndPeriod(year, period);
    }
    
    
}
