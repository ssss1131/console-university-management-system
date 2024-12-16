package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.StudentService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class StudentController {
    private StudentService studentService = new StudentService();

    public List<String> getDiscipline(Student student, String year, Period period) {
        return studentService.getDisciplineByYearAndPeriod(student, year, period);
    }
}
