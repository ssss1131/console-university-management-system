package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.TeacherService;

import java.util.Vector;

public class TeacherController {
    private TeacherService teacherService;

    public TeacherService getTeacherService() {
        return this.teacherService;
    }
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Vector<Student> getStudents() {
        //TODO
        return null;
    }

    public void sendMessage() {
        //TODO
    }

    public void addMarks() {
        //TODO
    }

    public void submitComplaint() {
        //TODO
    }

    public void defineFinalDate() {
        //TODO
    }
}
