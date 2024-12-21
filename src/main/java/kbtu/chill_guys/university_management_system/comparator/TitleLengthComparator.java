package main.java.kbtu.chill_guys.university_management_system.comparator;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.util.Comparator;

public class TitleLengthComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper o1, ResearchPaper o2) {
        return Integer.compare(o1.getTitle().length(), o2.getTitle().length());
    }
}
