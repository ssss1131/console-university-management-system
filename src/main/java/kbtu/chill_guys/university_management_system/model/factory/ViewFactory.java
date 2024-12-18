package main.java.kbtu.chill_guys.university_management_system.model.factory;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.view.*;
import main.java.kbtu.chill_guys.university_management_system.view.en.*;
import main.java.kbtu.chill_guys.university_management_system.view.kz.*;
import main.java.kbtu.chill_guys.university_management_system.view.ru.*;

public class ViewFactory {
    public static AdminView getAdminView(Language language) {
        return switch (language) {
            case EN -> new AdminViewEn();
            case RU -> new AdminViewRu();
            case KZ -> new AdminViewKz();
        };
    }

    public static AuthView getAuthView(Language language) {
        return switch (language) {
            case EN -> new AuthViewEn();
            case RU -> new AuthViewRu();
            case KZ -> new AuthViewKz();
        };
    }

    public static DeanView getDeanView(Language language) {
        return switch (language) {
            case EN -> new DeanViewEn();
            case RU -> new DeanViewRu();
            case KZ -> new DeanViewKz();
        };
    }

    public static GeneralView getGeneralView(Language language) {
        return switch (language) {
            case EN -> new GeneralViewEn();
            case RU -> new GeneralViewRu();
            case KZ -> new GeneralViewKz();
        };
    }

    public static JournalView getJournalView(Language language) {
        return switch (language) {
            case EN -> new JournalViewEn();
            case RU -> new JournalViewRu();
            case KZ -> new JournalViewKz();
        };
    }

    public static ManagerView getManagerView(Language language) {
        return switch (language) {
            case EN -> new ManagerViewEn();
            case RU -> new ManagerViewRu();
            case KZ -> new ManagerViewKz();
        };
    }

    public static StudentView getStudentView(Language language) {
        return switch (language) {
            case EN -> new StudentViewEn();
            case RU -> new StudentViewRu();
            case KZ -> new StudentViewKz();
        };
    }
}
