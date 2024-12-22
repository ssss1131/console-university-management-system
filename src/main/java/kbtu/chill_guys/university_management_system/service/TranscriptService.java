package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TraditionalGrade;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.storage.TranscriptStorage;

public class TranscriptService {
    private final TranscriptStorage storage = TranscriptStorage.getInstance();

    public String convertToGpaLetter(double totalGrade) {
        if (totalGrade >= 95) return "A";
        if (totalGrade >= 90) return "A_MINUS";
        if (totalGrade >= 85) return "B_PLUS";
        if (totalGrade >= 80) return "B";
        if (totalGrade >= 75) return "B_MINUS";
        if (totalGrade >= 70) return "C_PLUS";
        if (totalGrade >= 65) return "C";
        if (totalGrade >= 60) return "C_MINUS";
        if (totalGrade >= 55) return "D_PLUS";
        if (totalGrade >= 50) return "D";
        return "F";
    }

    public TraditionalGrade getTraditionalGrade(double totalGrade) {
        Gpa gpa = Gpa.valueOf(convertToGpaLetter(totalGrade));
        return TraditionalGrade.fromGpa(gpa);
    }

    public void saveTranscript(Transcript transcript) {
        storage.addTranscript(transcript);
        TranscriptStorage.saveToFile();
    }

    public Transcript getTranscriptForDiscipline(Student student, Discipline discipline) {
        return storage.getTranscriptsForStudent(student.getId()).stream()
                .filter(transcript -> transcript.getSubject().equals(discipline))
                .findFirst()
                .orElse(null);
    }
}
