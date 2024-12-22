package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;

import java.io.Serializable;
import java.time.LocalDate;

public class LessonRecord implements Serializable {
    private final LocalDate date;
    private final String lesson;
    private Attendance attendance;
    private double grade;
    private String comment;

    public LessonRecord(LocalDate date, String lesson, Attendance attendance, double grade, String comment) {
        this.date = date;
        this.lesson = lesson;
        this.attendance = attendance;
        this.grade = grade;
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLesson() {
        return lesson;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("Grade must be between 0 and 100.");
        }
        this.grade = grade;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "LessonRecord{" +
                "date=" + date +
                ", lesson='" + lesson + '\'' +
                ", attendance=" + attendance +
                ", grade=" + grade +
                ", comment='" + comment + '\'' +
                '}';
    }
}
