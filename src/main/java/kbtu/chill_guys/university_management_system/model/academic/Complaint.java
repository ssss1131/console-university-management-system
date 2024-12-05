package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.time.LocalDate;

public class Complaint {
    private Student student;
    private String description;
    private LocalDate date;
    
    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getLocalDate() {
        return this.date;
    }

    public void setLocalDate(LocalDate date) {
        this.date = date;
    }
}
