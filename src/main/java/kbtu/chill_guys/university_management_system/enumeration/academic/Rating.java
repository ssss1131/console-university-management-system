package main.java.kbtu.chill_guys.university_management_system.enumeration.academic;

public enum Rating {
    VERY_POOR(10),
    POOR(30),
    AVERAGE(50),
    GOOD(70),
    EXCELLENT(90);

    private final int score;

    Rating(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public static Rating fromScore(int score) {
        for (Rating rating : values()) {
            if (score <= rating.score) {
                return rating;
            }
        }
        return VERY_POOR;
    }
}
