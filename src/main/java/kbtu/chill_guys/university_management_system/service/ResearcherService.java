package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.Journal;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.ResearchPaperRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.ResearchProjectRepository;
import main.java.kbtu.chill_guys.university_management_system.repository.ResearchersRepository;
import main.java.kbtu.chill_guys.university_management_system.storage.JournalStorage;

import java.util.*;

public class ResearcherService {

    private static final ResearcherService instance = new ResearcherService();

    private final ResearchersRepository researchersRepository = ResearchersRepository.getInstance();
    private final ResearchPaperRepository researchPaperRepository = ResearchPaperRepository.getInstance();
    private final ResearchProjectRepository researchProjectRepository = ResearchProjectRepository.getInstance();
    private final JournalStorage storage = JournalStorage.getInstance();

    private ResearcherService() {
    }

    public static ResearcherService getInstance() {
        return instance;
    }

    public boolean isResearcher(User loggedUser) {
        return researchersRepository.isResearcher(loggedUser);
    }

    public boolean addResearcher(User researcher) {
        if (!isResearcher(researcher)) {
            researchersRepository.addLine(researcher);
            return true;
        } else {
            return false;
        }
    }

    public List<User> getAllResearchersExceptAuthor() {
        User user = Menu.getInstance().getLoggedUser();
        Vector<User> users = researchersRepository.getAllLines();
        users.remove(user);
        return users;
    }

    public boolean isDOIUnique(String doi) {
        return researchPaperRepository.getAllLines()
                .stream()
                .noneMatch(researchPaper -> researchPaper.getDoi().equalsIgnoreCase(doi.trim()));
    }

    public List<ResearchPaper> getResearchPapers(User user) {
        Vector<ResearchPaper> papers = researchPaperRepository.getAllLines();

        List<ResearchPaper> userPapers = new ArrayList<>();
        for (ResearchPaper paper : papers) {
            if (paper.getAuthors().contains(user)) {
                userPapers.add(paper);
            }
        }

        return userPapers;
    }

    public List<ResearchProject> getResearchProjects(User user) {
        Vector<ResearchProject> projects = researchProjectRepository.getAllLines();
        List<ResearchProject> userProjects = new ArrayList<>();
        for (ResearchProject project : projects) {
            if(project.getParticipants().contains(user)){
                userProjects.add(project);
            }
        }
        return userProjects;
    }

    public void addNewResearchPaper(ResearchPaper researchPaper) {
        researchPaperRepository.addLine(researchPaper);
        Journal journal = researchPaper.getJournal();
        saveMaybeNewJournal(journal);
        journal.addPost(researchPaper);
        storage.addResearchPaper(journal, researchPaper);
    }

    public void addNewResearchProject(ResearchProject project) {
        researchProjectRepository.addLine(project);
        Journal journal = project.getJournal();
        saveMaybeNewJournal(journal);
        journal.addProject(project);
        storage.addResearchProject(journal, project);
    }

    private void saveMaybeNewJournal(Journal journal) {
        boolean newJournal = storage.isNewJournal(journal);
        if (newJournal) {
            storage.addNewJournal(journal);
        }
    }

    public List<ResearchPaper> getResearchPapers() {
        return researchPaperRepository.getAllLines();
    }

    public List<User> getResearchers(){
        return researchersRepository.getAllLines();
    }

    public User getTopCitedResearcherBySchool(School school) {
        List<User> researchers = getResearchers();
        return researchers.stream()
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .filter(user -> user.getSchool().equals(school))
                .max(Comparator.comparingInt(this::calculateTotalCitations)).orElse(null);
    }

    public User getTopCitedResearcherByYear(int year) {
        List<User> researchers = getResearchers();
        return researchers.stream()
                .max(Comparator.comparingInt(user -> calculateCitationsByYear(user, year)))
                .orElse(null);
    }

    public int calculateTotalCitations(User researcher) {
        Vector<ResearchPaper> researchPapers = researchPaperRepository.getAllLines();
        return researchPapers.stream()
                .filter(paper -> paper.getAuthors().contains(researcher))
                .mapToInt(ResearchPaper::getCitations)
                .sum();
    }


    private int calculateCitationsByYear(User researcher, int year) {
        Vector<ResearchPaper> researchPapers = researchPaperRepository.getAllLines();
        return researchPapers.stream()
                .filter(paper -> paper.getAuthors().contains(researcher))
                .filter(paper -> paper.getPublicationDate().getYear() == year)
                .mapToInt(ResearchPaper::getCitations)
                .sum();
    }

    public List<Integer> getAllPublicationYears() {
        Vector<ResearchPaper> researchPapers = researchPaperRepository.getAllLines();
        return researchPapers.stream()
                .map(paper -> paper.getPublicationDate().getYear())
                .distinct()
                .sorted()
                .toList();
    }

}
