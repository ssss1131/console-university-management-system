package main.java.kbtu.chill_guys.university_management_system.view;

public interface AuthView {
    String getEmail();

    String getPassword();

    void displayLoginSuccess(String userFirstName, String userLastName);

    void displayLoginFailure();

    void displayLogoutMessage(boolean isLoggedIn);
}
