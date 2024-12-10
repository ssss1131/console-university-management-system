package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.Serializable;
import java.util.Objects;

public class Transcript implements Serializable {
    private Student student;
    private Discipline discipline;
    private int year;
    private Period period;
    private int gpaNumeric;
    private Gpa gpaLetter;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Discipline getSubject() {
        return discipline;
    }

    public void setSubject(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Gpa getGpaLetter() {
        return gpaLetter;
    }

    public void setGpaLetter(Gpa gpaLetter) {
        this.gpaLetter = gpaLetter;
    }

    public int getGpaNumeric() {
        return gpaNumeric;
    }

    public void setGpaNumeric(int gpaNumeric) {
        this.gpaNumeric = gpaNumeric;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transcript that = (Transcript) o;
        return year == that.year && gpaNumeric == that.gpaNumeric && Objects.equals(student, that.student) && Objects.equals(discipline, that.discipline) && period == that.period && gpaLetter == that.gpaLetter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, discipline, year, period, gpaNumeric, gpaLetter);
    }

    @Override
    public String toString() {
        return "Transcript{" +
               "student=" + student +
               ", subject=" + discipline +
               ", year=" + year +
               ", period=" + period +
               ", gpaNumeric=" + gpaNumeric +
               ", gpaLetter=" + gpaLetter +
               '}';
    }
}
