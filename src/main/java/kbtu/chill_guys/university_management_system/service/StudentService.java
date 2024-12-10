package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentService {


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

    // Проверяет, доступен ли выбранный год
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

    // Проверяет, доступен ли выбранный период
    public boolean isPeriodValid(Student student, String selectedYear, Period selectedPeriod) {
        return student.getSemesterDisciplines().keySet().stream()
                .anyMatch(semester ->
                        (semester.getYearStart() + "-" + semester.getYearEnd()).equals(selectedYear)
                        && semester.getPeriod() == selectedPeriod);
    }

    // Возвращает курсы по выбранному году и периоду
    public List<String> getCoursesByYearAndPeriod(Student student, String year, Period period) {
        return student.getSemesterDisciplines().entrySet().stream()
                .filter(entry -> {
                    Semester semester = entry.getKey();
                    return (semester.getYearStart() + "-" + semester.getYearEnd()).equals(year)
                           && semester.getPeriod() == period;
                })
                .map(entry -> entry.getValue().getName())
                .collect(Collectors.toList());
    }


}
