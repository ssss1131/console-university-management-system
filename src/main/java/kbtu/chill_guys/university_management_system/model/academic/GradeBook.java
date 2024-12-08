package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Attendance;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GradeBook implements Serializable {

    private Student student;
    private Subject subject;
    private final Map<LocalDate, AttendanceAndGrade> attendanceAndGrades = new HashMap<>();

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Map<LocalDate, AttendanceAndGrade> getAttendanceAndGrades() {
        return this.attendanceAndGrades;
    }

    public void addAttendanceAndGrade(LocalDate date, Attendance attendance, double grade) {
        this.attendanceAndGrades.put(date, new AttendanceAndGrade(attendance, grade));
    }

    public void updateAttendanceAndGrade(LocalDate date, Attendance attendance, double grade) {
        if (this.attendanceAndGrades.containsKey(date)) {
            this.attendanceAndGrades.put(date, new AttendanceAndGrade(attendance, grade));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeBook gradeBook = (GradeBook) o;
        return Objects.equals(student, gradeBook.student) && Objects.equals(subject, gradeBook.subject) && Objects.equals(attendanceAndGrades, gradeBook.attendanceAndGrades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, subject, attendanceAndGrades);
    }


    @Override
    public String toString() {
        return "GradeBook{" +
               "student=" + student +
               ", subject=" + subject +
               ", attendanceAndGrades=" + attendanceAndGrades +
               '}';
    }

    public static class AttendanceAndGrade implements Serializable{
        private Attendance attendance;
        private double grade;

        public AttendanceAndGrade(Attendance attendance, double grade) {
            this.attendance = attendance;
            this.grade = grade;
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
            this.grade = grade;
        }

        @Override
        public String toString() {
            return "Attendance: " + attendance + ", Grade: " + grade;
        }
    }
}
