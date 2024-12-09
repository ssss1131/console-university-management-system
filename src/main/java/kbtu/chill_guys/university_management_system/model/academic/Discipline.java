package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.CourseType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class Discipline implements Serializable {
    private School school;
    private String code;
    private String name;
    private Integer credits;
    private GradeBook gradebook;
    private Vector<Discipline> prerequisites;
    private CourseType courseType;

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return this.credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public GradeBook getGradebook() {
        return this.gradebook;
    }

    public void setGradebook(GradeBook gradebook) {
        this.gradebook = gradebook;
    }

    public Vector<Discipline> getPrerequisites() {
        return this.prerequisites;
    }

    public void setPrerequisites(Vector<Discipline> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public CourseType getCourseType() {
        return this.courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline discipline = (Discipline) o;
        return school == discipline.school && Objects.equals(code, discipline.code) && Objects.equals(name, discipline.name) && Objects.equals(credits, discipline.credits) && Objects.equals(gradebook, discipline.gradebook) && Objects.equals(prerequisites, discipline.prerequisites) && courseType == discipline.courseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(school, code, name, credits, gradebook, prerequisites, courseType);
    }

    @Override
    public String toString() {
        return "Subject{" +
               "school=" + school +
               ", code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", credits=" + credits +
               ", gradebook=" + gradebook +
               ", prerequisites=" + prerequisites +
               ", courseType=" + courseType +
               '}';
    }

    public double getMinandMax() {
        //TODO
        return 0.0;
    }

    public void viewGradebook() {
        //TODO
    }

    public void getTranscript() {
        //TODO
    }
}
