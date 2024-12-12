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


    public StudentService getStudentService() {
        return this.studentService;
    }
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

//    public Vector<Mark> getMarks() {
//        //TODO
//        return null;
//    }

    public Vector<Transcript> getTranscript() {
        //TODO
        return null;
    }

    public Vector<Organization> getOrganizations() {
        //TODO
        return null;
    }

    public Vector<Teacher> getTeachers() {
        //TODO
        return null;
    }

//    public Vector<Course> getDisipline() {
//        //TODO
//        return null;
//    }

    public Map<LocalDate, Boolean> getAttendance() {
        //TODO
        return null;
    }

    public int getAcademicStanding() {
        //TODO
        return 0;
    }


    public boolean registerForCourse() {
        //TODO
        return false;
    }

    public boolean dropCourse() {
        //TODO
        return false;
    }

    public void rateTeacher() {
        //TODO
    }

    public Transcript viewTranscript() {
        //TODO
        return null;
    }
//
//    public Vector<Mark> viewMarks() {
//        //TODO
//        return null;
//    }

    public boolean joinOrganization() {
        //TODO
        return false;
    }

    public boolean leaveOrganization() {
        //TODO
        return false;
    }

//    public Map<Course, Integer> viewAttendance() {
//        //TODO
//        return null;
//    }

    public String viewAcademicStanding() {
        //TODO
        return "";
    }

    public List<String> getDiscipline(Student student, String year, Period period) {
        // Проверка года
        if (!studentService.isYearValid(student, year)) {
            throw new IllegalArgumentException("Invalid year. Please enter a valid year.");
        }

        // Проверка периода
//        if (!studentService.isPeriodValid(student, year, period)) {
//            throw new IllegalArgumentException("Invalid period. Please enter a valid period.");
//        }

        // Возврат курсов
        return studentService.getDisciplineByYearAndPeriod(student, year, period);
    }
}
