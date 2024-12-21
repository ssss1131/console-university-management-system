package main.java.kbtu.chill_guys.university_management_system.comparator;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.util.Comparator;

public class PublicationDateComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper o1, ResearchPaper o2) {
        return o1.getPublicationDate().compareTo(o2.getPublicationDate());
    }
}

