package main.java.kbtu.chill_guys.university_management_system.service;

import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.repository.DisciplineRepository;

import java.util.List;
import java.util.Optional;
import java.util.Vector;

public class DisciplineService {

    DisciplineRepository disciplineRepository = new DisciplineRepository();

    public List<Discipline> getDisciplinesBySchool(School school) {
        return disciplineRepository.allDisciplinesInSchool(school);
    }

    public boolean isUniqueCode(String code) {
        Vector<Discipline> all = disciplineRepository.getAll();
        Optional<Discipline> first = all.stream()
                .filter(discipline -> discipline.getCode().equalsIgnoreCase(code))
                .findFirst();
        return first.isEmpty();
    }

    public List<Discipline> getAllDisciplines(){
        return disciplineRepository.getAll();
    }
}
