package main.java.kbtu.chill_guys.university_management_system.model.academic;

import universityManagementSystem.models.student.Student;

import java.time.LocalDate;

public class Complaint {
    private Student student;
    private String description;
    private LocalDate date;
    
    private Student getStudent() {
        return this.student;
    }

    private void setStudent(Student student) {
        this.student = student;
    }

    private String getDescription() {
        return this.description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private LocalDate getLocalDate() {
        return this.date;
    }

    private void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
