package main.java.kbtu.chill_guys.university_management_system.model.academic;

import java.util.Vector;

public class Transcript {
    private Vector<Mark> grades;
    
    public Vector<Mark> getGrades() {
        return this.grades;
    }

    public void setGrades(Vector<Mark> grades) {
        this.grades = grades;
    }
}
