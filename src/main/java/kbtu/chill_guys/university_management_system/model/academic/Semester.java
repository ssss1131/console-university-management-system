package main.java.kbtu.chill_guys.university_management_system.model.academic;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;

import java.io.Serializable;
import java.util.Objects;

public class Semester implements Serializable, Comparable<Semester> {
    private final int year;
    private final Period period;

    public Semester(int year, Period period) {
        this.year = year;
        this.period = period;
    }

    public int getYear() {
        return year;
    }


    public Period getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return year + " " + period;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return year == semester.year && period == semester.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, period);
    }

    @Override
    public int compareTo(Semester semester) {

        if (this.year != semester.year) {
            return Integer.compare(this.year, semester.year);
        }

        return this.period == semester.period ? 0 :
                this.period == Period.FALL ? 1 : -1;

    }

}

