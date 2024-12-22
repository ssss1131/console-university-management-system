package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
import main.java.kbtu.chill_guys.university_management_system.repository.UserRepository;
import main.java.kbtu.chill_guys.university_management_system.service.ResearcherService;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.StudentView;

import java.util.*;

public class StudentViewKz implements StudentView {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void displayAvailableDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Тіркелу үшін қолжетімді пәндер жоқ.");
            return;
        }

        System.out.println("Тіркелу үшін қолжетімді пәндер:");
        for (int i = 0; i < disciplines.size(); i++) {
            Discipline discipline = disciplines.get(i);
            System.out.printf("%d. %s (Коды: %s, Кредиттер: %d)%n",
                    i + 1, discipline.getName(), discipline.getCode(), discipline.getCredits());
        }
    }

    @Override
    public List<Discipline> selectDisciplinesForRegistration(List<Discipline> availableDisciplines) {
        System.out.println("Тіркелу үшін пән нөмірлерін енгізіңіз (үтірмен бөлінген):");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split(",");
                List<Discipline> selectedDisciplines = Arrays.stream(parts)
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .filter(i -> i >= 1 && i <= availableDisciplines.size())
                        .mapToObj(i -> availableDisciplines.get(i - 1))
                        .toList();

                if (selectedDisciplines.isEmpty()) {
                    System.out.println("Пәндер таңдалған жоқ. Қайтадан көріңіз.");
                } else {
                    return selectedDisciplines;
                }
            } catch (NumberFormatException e) {
                System.out.println("Қате енгізу. Пәндердің нөмірлерін үтірмен бөлініп енгізіңіз.");
            }
        }
    }

    @Override
    public void displayRegistrationConfirmation(List<Discipline> registeredDisciplines) {
        System.out.println("Сіз келесі пәндерге сәтті тіркелдіңіз:");
        registeredDisciplines.forEach(discipline ->
                System.out.printf("- %s (Коды: %s, Кредиттер: %d)%n",
                        discipline.getName(), discipline.getCode(), discipline.getCredits()));
    }

    @Override
    public void displayInfo(Student student) {
        System.out.println("Сәлеметсіз бе, " + student.getFirstName() + " " + student.getLastName());
        System.out.println("Сіздің ID: " + student.getId());
        System.out.println("Оқу ұзақтығы: " + student.getStudyDuration());
        System.out.println("Мектеп: " + student.getSchool());
        System.out.println("Ұйым: " + student.getOrganization());
        System.out.println("GPA: " + student.getGpa());
    }

    @Override
    public void showRegisteredDisciplines(List<Discipline> disciplines, Semester semester) {
        System.out.println("\n=== Тіркелген пәндер ===");
        System.out.println("Семестр: " + semester);

        if (disciplines.isEmpty()) {
            System.out.println("Осы семестрде пәндерге тіркелмегенсіз.");
            return;
        }

        showDisciplineList(disciplines);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public Semester getSemester(Set<Semester> semesters) {
        System.out.println("Белгілі бір семестрдегі немесе барлық семестрлердегі пәндерді көргіңіз келе ме?");
        System.out.println("1. Белгілі бір семестр");
        System.out.println("2. Барлық семестрлер");

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, 2);

        if (choice == 2) {
            return null;
        }

        if (semesters.isEmpty()) {
            System.out.println("Қолжетімді семестрлер жоқ.");
            return null;
        }

        System.out.println("Семестрді таңдаңыз:");
        List<Semester> semesterList = new ArrayList<>(semesters);
        for (int i = 0; i < semesterList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, semesterList.get(i));
        }

        int semesterChoice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, semesterList.size());
        return semesterList.get(semesterChoice - 1);
    }

    @Override
    public void showExactDisciplines(List<Discipline> disciplines) {
        if (disciplines == null || disciplines.isEmpty()) {
            System.out.println("Таңдалған семестрге тіркелген пәндер жоқ.");
            return;
        }

        System.out.println("\n=== Таңдалған семестр үшін тіркелген пәндер ===");
        showDisciplineList(disciplines);
    }

    @Override
    public void showAllDisciplines(Map<Semester, List<Discipline>> disciplinesBySemester) {
        if (disciplinesBySemester == null || disciplinesBySemester.isEmpty()) {
            System.out.println("Семестрлер бойынша тіркелген пәндер жоқ.");
            return;
        }

        System.out.println("\n=== Барлық тіркелген пәндер ===");
        for (Map.Entry<Semester, List<Discipline>> entry : disciplinesBySemester.entrySet()) {
            Semester semester = entry.getKey();
            List<Discipline> disciplines = entry.getValue();

            System.out.println("Семестр: " + semester);
            showDisciplineList(disciplines);
        }
    }

    @Override
    public void showDiploma(DiplomaProject project, GraduateStudent student) {
        while (true) {
            System.out.println("\n=== Дипломдық Жоба Толығы ===");
            System.out.printf("Тақырып: %s%n", project.getTitle());
            System.out.printf("Сипаттама: %s%n", project.getDescription());
            System.out.printf("Ғылыми жетекші: %s%n",
                    project.getSupervisor() != null ? project.getSupervisor().getFirstName() : "Таңдалмаған");
            System.out.println("Жарияланған мақалалар:");
            if (project.getPublishedPapers().isEmpty()) {
                System.out.println("  Жарияланған мақалалар жоқ.");
            } else {
                for (int i = 0; i < project.getPublishedPapers().size(); i++) {
                    ResearchPaper paper = project.getPublishedPapers().get(i);
                    System.out.printf("  %d. %s (DOI: %s)%n", i + 1, paper.getTitle(), paper.getDoi());
                }
            }

            System.out.println("\nӘрекеттер:");
            System.out.println("1. Тақырыпты өзгерту");
            System.out.println("2. Сипаттаманы өзгерту");
            System.out.println("3. Мақалаларды қосу");
            System.out.println("4. Шығу жане сактау");

            int choice = InputValidatorUtil.validateIntegerInput("Әрекетті таңдаңыз: ", 1, 4);

            switch (choice) {
                case 1 -> {
                    System.out.println("Жаңа тақырыпты енгізіңіз:");
                    String newTitle = InputValidatorUtil.validateNonEmptyInput("Тақырып бос болмауы керек.");
                    project.setTitle(newTitle);
                    System.out.println("Тақырып сәтті жаңартылды.");
                }
                case 2 -> {
                    System.out.println("Жаңа сипаттаманы енгізіңіз:");
                    String newDescription = InputValidatorUtil.validateNonEmptyInput("Сипаттама бос болмауы керек.");
                    project.setDescription(newDescription);
                    System.out.println("Сипаттама сәтті жаңартылды.");
                }
                case 3 -> {
                    System.out.println("Қосылатын мақалаларды таңдаңыз:");
                    List<ResearchPaper> availablePapers = ResearcherService.getInstance().getResearchPapers(student)
                            .stream().filter(researchPaper -> !project.getPublishedPapers().contains(researchPaper))
                            .toList();
                    Vector<ResearchPaper> selectedPapers = selectResearchPapers(availablePapers);
                    project.getPublishedPapers().addAll(selectedPapers);
                    System.out.println("Мақалалар дипломдық жобаға сәтті қосылды.");
                }
                case 4 -> {
                    System.out.println("Дипломдық жоба терезесінен шығу.");
                    return;
                }
            }
        }
    }

    private Vector<ResearchPaper> selectResearchPapers(List<ResearchPaper> papers) {
        if (papers.isEmpty()) {
            System.out.println("Қолжетімді мақалалар жоқ.");
            return new Vector<>();
        }

        System.out.println("Қолжетімді мақалалар:");
        for (int i = 0; i < papers.size(); i++) {
            System.out.printf("%d. %s (DOI: %s)%n", i + 1, papers.get(i).getTitle(), papers.get(i).getDoi());
        }

        System.out.println("Мақала нөмірлерін енгізіңіз (үтірмен бөліңіз) немесе өткізіп жіберу үшін Enter басыңыз:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();

        Vector<ResearchPaper> selectedPapers = new Vector<>();
        if (!input.isEmpty()) {
            try {
                String[] indices = input.split(",");
                for (String index : indices) {
                    int paperIndex = Integer.parseInt(index.trim()) - 1;
                    if (paperIndex >= 0 && paperIndex < papers.size()) {
                        selectedPapers.add(papers.get(paperIndex));
                    } else {
                        System.out.println("Қате нөмір: " + (paperIndex + 1));
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Қате енгізу. Таңдау өткізіледі.");
            }
        }

        return selectedPapers;
    }


    private void showDisciplineList(List<Discipline> disciplines) {
        System.out.printf("%-10s %-30s %-10s %-15s%n", "Код", "Атауы", "Кредиттер", "Курс түрі");
        System.out.println("---------------------------------------------------------------");

        for (Discipline discipline : disciplines) {
            System.out.printf("%-10s %-30s %-10d %-15s%n",
                    discipline.getCode(),
                    discipline.getName(),
                    discipline.getCredits(),
                    discipline.getCourseType());
        }
    }
}
