package main.java.kbtu.chill_guys.university_management_system.model.employee;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.research.Researcher;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class ResearchSupervisor extends Employee implements Researcher {
    private Integer hIndex;
    private Vector<ResearchProject> supervisedProjects ;
    private Vector<GraduateStudent> supervisedStudents ;
    private Vector<ResearchPaper> supervisedResearchPapers;

    public Integer getHIndex() {
        return this.hIndex;
    }

    public void setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    public Vector<ResearchProject> getSupervisedProjects () {
        return this.supervisedProjects ;
    }

    public void setSupervisedProjects (Vector<ResearchProject> supervisedProjects ) {
        this.supervisedProjects  = supervisedProjects ;
    }

    public Vector<GraduateStudent> getSupervisedStudents () {
        return this.supervisedStudents ;
    }

    public void setSupervisedStudents (Vector<GraduateStudent> supervisedStudents ) {
        this.supervisedStudents  = supervisedStudents ;
    }

    public Vector<ResearchPaper> getSupervisedResearchPapers() {
        return this.supervisedResearchPapers;
    }

    public void setSupervisedResearchPapers(Vector<ResearchPaper> supervisedResearchPapers) {
        this.supervisedResearchPapers = supervisedResearchPapers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResearchSupervisor that = (ResearchSupervisor) o;
        return Objects.equals(hIndex, that.hIndex) && Objects.equals(supervisedProjects, that.supervisedProjects) && Objects.equals(supervisedStudents, that.supervisedStudents) && Objects.equals(supervisedResearchPapers, that.supervisedResearchPapers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hIndex, supervisedProjects, supervisedStudents, supervisedResearchPapers);
    }

    @Override
    public String toString() {
        return "ResearchSupervisor{" +
               "hIndex=" + hIndex +
               ", supervisedProjects=" + supervisedProjects +
               ", supervisedStudents=" + supervisedStudents +
               ", supervisedResearchPapers=" + supervisedResearchPapers +
               "} " + super.toString();
    }
}
