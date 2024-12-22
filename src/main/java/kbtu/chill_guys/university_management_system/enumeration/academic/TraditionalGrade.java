package main.java.kbtu.chill_guys.university_management_system.enumeration.academic;

import java.util.EnumSet;

public enum TraditionalGrade {
    EXCELLENT(EnumSet.of(Gpa.A, Gpa.A_MINUS)),
    GOOD(EnumSet.of(Gpa.B_PLUS, Gpa.B, Gpa.B_MINUS)),
    SATISFACTORY(EnumSet.of(Gpa.C_PLUS, Gpa.C, Gpa.C_MINUS, Gpa.D_PLUS, Gpa.D)),
    UNSATISFACTORY(EnumSet.of(Gpa.F));

    private final EnumSet<Gpa> gpaSet;

    TraditionalGrade(EnumSet<Gpa> gpaSet) {
        this.gpaSet = gpaSet;
    }

    public static TraditionalGrade fromGpa(Gpa gpa) {
        for (TraditionalGrade grade : TraditionalGrade.values()) {
            if (grade.gpaSet.contains(gpa)) {
                return grade;
            }
        }
        return null;
    }
}
