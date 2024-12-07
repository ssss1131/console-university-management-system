package main.java.kbtu.chill_guys.university_management_system.model.academic;

public class GPA {
    private double numeric;
    private String letter;

    public GPA() {}

    public GPA(double numeric, String letter) {
        this.numeric = numeric;
        this.letter = letter;
    }
    
    public double getNumeric() {
        return this.numeric;
    }

    public void setNumeric(double numeric) {
        this.numeric = numeric;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
}
