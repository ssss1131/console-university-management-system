package main.java.kbtu.chill_guys.university_management_system.model.academic;


import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;

import java.util.Vector;

public class Course extends Subject {
    private Vector<Subject> subjects;
    private Period period;
    private Integer limit ;
    private Integer year;

    public Vector<Subject> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(Vector<Subject> subjects) {
        this.subjects = subjects;
    }

    public Period getPeriod() {
        return this.period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Integer getLimit () {
        return this.limit ;
    }

    public void setLimit (Integer limit ) {
        this.limit  = limit ;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void displayReport() {
        //TODO
    }
}
