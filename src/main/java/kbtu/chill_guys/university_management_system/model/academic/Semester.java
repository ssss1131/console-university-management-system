package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;

import java.io.Serializable;
import java.util.Objects;

public class Semester implements Serializable {
    private int yearStart;
    private int yearEnd;
    private Period period;

    public Semester(int yearStart, int yearEnd, Period period) {
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.period = period;
    }

    public int getYearStart() {
        return yearStart;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public Period getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return yearStart + "-" + yearEnd + " " + period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return yearStart == semester.yearStart && yearEnd == semester.yearEnd && period.equals(semester.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(yearStart, yearEnd, period);
    }
}

