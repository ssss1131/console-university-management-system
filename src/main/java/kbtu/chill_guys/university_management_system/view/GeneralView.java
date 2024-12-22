package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.util.List;
import java.util.Set;

public interface GeneralView {
    School showSchoolSelection(List<School> schools);

    void displayDisciplines(List<Discipline> disciplines);

    Journal selectJournal(Set<Journal> journals);

    void showPosts(List<ResearchPaper> papers, List<ResearchProject> projects);
}