package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Format;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;

import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public interface ResearcherView {

    void displayInvalidUser();
    void displaySuccessBecomingResearcher();
    void displayAlreadyIsResearcher();

    ResearchPaper getResearchPaper();

    Journal selectJournal();

    Journal createNewJournal();

    String generateDOI();

    Vector<User> selectAuthors(List<User> researchers);

    void displayResearchPapers(List<ResearchPaper> papers);

    ResearchProject getResearchProject();

    Vector<ResearchPaper> selectResearchPapers(Vector<User> participants);

    void displayResearchProjects(List<ResearchProject> projects);

    Comparator<ResearchPaper> getSortPreference();

    School getSchool();

    void showTopCitedResearcher(User user, int totalCitations, int year);

    void showTopCitedResearcherOfSchool(User user, School school, int totalCitations);

    int selectPublicationYear(List<Integer> years);

    ResearchPaper selectResearchPaper(List<ResearchPaper> papers);

    Format selectCitationFormat();

    void displayCitation(ResearchPaper paper, Format format);
}
