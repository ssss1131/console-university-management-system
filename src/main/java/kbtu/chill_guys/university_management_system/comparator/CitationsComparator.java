package main.java.kbtu.chill_guys.university_management_system.comparator;

import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;

import java.util.Comparator;

public class CitationsComparator implements Comparator<ResearchPaper> {
    @Override
    public int compare(ResearchPaper o1, ResearchPaper o2) {
        return Integer.compare(o2.getCitations(), o1.getCitations()); // Сортировка по убыванию
    }
}


