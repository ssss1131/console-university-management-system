package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.UrgencyLevel;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;

import java.io.Serializable;
import java.util.UUID;

public class Complaint implements Serializable {
    private final UUID id;
    private final Teacher teacher;
    private final Student student;
    private final Discipline discipline;
    private final String comment;
    private final UrgencyLevel urgencyLevel;
    private Status status;

    public Complaint(Teacher teacher, Student student, Discipline discipline, String comment, UrgencyLevel urgencyLevel) {
        this.id = UUID.randomUUID();
        this.teacher = teacher;
        this.student = student;
        this.discipline = discipline;
        this.comment = comment;
        this.urgencyLevel = urgencyLevel;
        this.status = Status.PENDING_APPROVAL;
    }

    public UUID getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Student getStudent() {
        return student;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public String getComment() {
        return comment;
    }

    public UrgencyLevel getUrgencyLevel() {
        return urgencyLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void assign() {
        this.status = Status.ASSIGNED;
    }

    public void reject() {
        this.status = Status.CANCELLED;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", teacher=" + teacher.getFirstName() + " " + teacher.getLastName() +
                ", student=" + student.getFirstName() + " " + student.getLastName() +
                ", discipline=" + discipline.getName() +
                ", urgencyLevel=" + urgencyLevel +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }
}
