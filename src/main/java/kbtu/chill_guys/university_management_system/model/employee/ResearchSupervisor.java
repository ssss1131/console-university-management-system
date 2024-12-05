package main.java.kbtu.chill_guys.university_management_system.model.employee;

import universityManagementSystem.models.research.ResearchPaper;
import universityManagementSystem.models.research.ResearchProject;
import universityManagementSystem.models.student.GraduateStudent;
import universityManagementSystem.models.student.research.Researcher;

import java.util.Vector;

public class ResearchSupervisor implements Researcher {
    private Integer hIndex;
    private Vector<ResearchProject> supervisedProjects ;
    private Vector<GraduateStudent> supervisedStudents ;
    private Vector<ResearchPaper> supervisedResearchPapers;

    private Integer getHIndex() {
        return this.hIndex;
    }

    private void setHIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    private Vector<ResearchProject> getSupervisedProjects () {
        return this.supervisedProjects ;
    }

    private void setSupervisedProjects (Vector<ResearchProject> supervisedProjects ) {
        this.supervisedProjects  = supervisedProjects ;
    }

    private Vector<GraduateStudent> getSupervisedStudents () {
        return this.supervisedStudents ;
    }

    private void setSupervisedStudents (Vector<GraduateStudent> supervisedStudents ) {
        this.supervisedStudents  = supervisedStudents ;
    }

    private Vector<ResearchPaper> getSupervisedResearchPapers() {
        return this.supervisedResearchPapers;
    }

    private void setSupervisedResearchPapers(Vector<ResearchPaper> supervisedResearchPapers) {
        this.supervisedResearchPapers = supervisedResearchPapers;
    }
}
