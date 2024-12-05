package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.time.LocalDate;
import java.util.Map;

public class GradeBook {
    private Student student;
    private Mark mark;
    private Map<LocalDate, Boolean> attendance;

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Mark getMark() {
        return this.mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    public Map<LocalDate, Boolean> getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Map<LocalDate, Boolean> attendance) {
        this.attendance = attendance;
    }
}
