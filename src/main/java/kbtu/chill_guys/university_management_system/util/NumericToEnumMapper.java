package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Rating;

public class NumericToEnumMapper {

    private NumericToEnumMapper() {
    }

    public static Gpa mapGpa(double numericGpa) {
        if (numericGpa >= 3.85) return Gpa.A;
        if (numericGpa >= 3.7) return Gpa.A_MINUS;
        if (numericGpa >= 3.3) return Gpa.B_PLUS;
        if (numericGpa >= 3.0) return Gpa.B;
        if (numericGpa >= 2.7) return Gpa.B_MINUS;
        if (numericGpa >= 2.3) return Gpa.C_PLUS;
        if (numericGpa >= 2.0) return Gpa.C;
        if (numericGpa >= 1.7) return Gpa.C_MINUS;
        if (numericGpa >= 1.3) return Gpa.D_PLUS;
        if (numericGpa >= 1.0) return Gpa.D;

        return Gpa.F;
    }

    public static Rating mapRating(int score){
        if (score < 20) {
            return Rating.VERY_POOR;
        } else if (score < 40) {
            return Rating.POOR;
        } else if (score < 60) {
            return Rating.AVERAGE;
        } else if (score < 80) {
            return Rating.GOOD;
        } else {
            return Rating.EXCELLENT;
        }
    }
}
