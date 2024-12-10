package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.enumeration.evaluation.Period;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;

import java.util.List;
import java.util.Vector;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.DISCIPLINE_PATH;

public class DisciplineRepository extends AbstractRepository<Discipline>{

    public DisciplineRepository() {
        super(DISCIPLINE_PATH);
    }

    public void save(Discipline discipline){
        addLine(discipline);
    }

    public void delete(Discipline discipline){
        removeLine(discipline);
    }

    public Vector<Discipline> getAllIn(){
        return getAllLines();
    }


    public List<String> findCoursesByYearAndPeriod(String year, Period period) {
        return null;
    }
}
