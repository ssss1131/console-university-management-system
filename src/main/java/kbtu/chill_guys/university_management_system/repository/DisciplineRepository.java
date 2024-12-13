package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.DISCIPLINE_PATH;

public class DisciplineRepository extends AbstractRepository<Discipline> {

    public DisciplineRepository() {
        super(DISCIPLINE_PATH);
    }

    public void save(Discipline discipline) {
        addLine(discipline);
    }

    public void delete(Discipline discipline) {
        removeLine(discipline);
    }

    public Vector<Discipline> getAll() {
        return getAllLines();
    }


    public List<String> findCoursesByYearAndPeriod(String year, Period period) {
        return null;
    }

    public List<Discipline> allDisciplinesInSchool(School school) {
        Vector<Discipline> all = getAll();
        return all.stream()
                .filter(discipline -> discipline.getSchool().equals(school))
                .collect(Collectors.toCollection(Vector::new));
    }
}
