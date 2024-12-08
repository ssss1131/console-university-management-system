package main.java.kbtu.chill_guys.university_management_system.controller;

import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.service.EmployeeService;

import java.util.Vector;

public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return this.employeeService;
    }
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void sendMessage() {
        //TODO
    }

    public Vector<Student> viewStudents() {
        //TODO
        return null;
    }
}
