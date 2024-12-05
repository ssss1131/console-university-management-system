package main.java.kbtu.chill_guys.university_management_system.dto;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Gpa;

public class TranscriptDto {
    private String code;
    private String name;
    private Integer credits;
    private double mark;
    private Gpa letterMark;
    private double gpa;

    public String getCode() {
        return this.code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getCredits() {
        return this.credits;
    }
    public void setCredits(Integer credits) {
        this.credits = credits;
    }
    public double getMark() {
        return this.mark;
    }
    public void setMark(double mark) {
        this.mark = mark;
    }
    public Gpa getLetterMark() {
        return this.letterMark;
    }
    public void setLetterMark(Gpa letterMark) {
        this.letterMark = letterMark;
    }
    public double getGpa() {
        return this.gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
