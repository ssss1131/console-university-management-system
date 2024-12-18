package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.Map;
import java.util.Vector;

public interface DeanView {
    Map<Discipline, Status> approveOrDeclineDisciplines(Vector<Discipline> allPendingDisciplines);
}

