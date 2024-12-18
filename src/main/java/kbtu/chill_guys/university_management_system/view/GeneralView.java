package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.List;

public interface GeneralView {
    School showSchoolSelection(List<School> schools);

    void displayDisciplines(List<Discipline> disciplines);
}