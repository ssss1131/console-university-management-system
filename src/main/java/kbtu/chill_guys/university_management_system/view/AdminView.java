package main.java.kbtu.chill_guys.university_management_system.view;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface AdminView {
    Map<String, Object> getUserInput();

    void displayUserCreated(User user);

    void displayMessage(String message);

    UUID getUserIdForDeletion();

    LogPeriod getLogPeriod();

    void displayLogs(List<String> logs);

    void handleResearchSupervisorInput(Map<String, Object> data);
}
