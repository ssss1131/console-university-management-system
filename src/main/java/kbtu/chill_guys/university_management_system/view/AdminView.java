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

    void displayUserAlreadyExists();

    void displayAllUsers(List<User> users);

    int getUserIndexForDeletion(int maxIndex);

    boolean confirmDeletion(User user);

    void displayNoUsersToDelete();

    void displayUserDeletionCancelled();

    void displayUserDeletedSuccessfully();

    Map<String, Object> getFieldsForUpdate(User user);

    void displayNoUsersForUpdate();

    void displayUserUpdatedSuccessfully();
}
