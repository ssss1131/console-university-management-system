package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.OrganizationRole;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.academic.LessonRecord;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Semester;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Transcript;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.model.research.ResearchPaper;
import main.java.kbtu.chill_guys.university_management_system.model.student.DiplomaProject;
import main.java.kbtu.chill_guys.university_management_system.model.student.GraduateStudent;
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
import main.java.kbtu.chill_guys.university_management_system.model.student.Student;
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

    @Override
    public String getOrganizationName() {
        System.out.println("Ұйымның атауын енгізіңіз:");
        return InputValidatorUtil.validateNonEmptyInput("Бос емес атау енгізіңіз.");
    }

    @Override
    public void showAlreadyExistingOrganizationName() {
        System.out.println("Мұндай атау қазірдің өзінде бар. Қайталанбайтын атау енгізіңіз.");
    }

    @Override
    public String getOrganizationDescription() {
        System.out.println("Ұйымның сипаттамасын енгізіңіз:");
        return InputValidatorUtil.validateNonEmptyInput("Бос емес сипаттама енгізіңіз.");
    }

    @Override
    public Organization selectOrganization(List<Organization> organizations) {
        if (organizations == null || organizations.isEmpty()) {
            System.out.println("Қол жетімді ұйымдар жоқ.");
            return null;
        }

        System.out.println("\n=== Қол жетімді ұйымдар ===");
        for (int i = 0; i < organizations.size(); i++) {
            Organization organization = organizations.get(i);
            System.out.printf("%d. %s - %s%n", i + 1, organization.getName(), organization.getDescription());
            if (organization.getMembers().isEmpty()) {
                System.out.println("   Қатысушылар: Қатысушылар жоқ.");
            } else {
                System.out.println("   Қатысушылар:");
                organization.getMembers().forEach((student, role) ->
                        System.out.printf("      - %s %s (%s)%n", student.getFirstName(), student.getLastName(), role));
            }
        }
        System.out.println("Ұйымды нөмір бойынша таңдаңыз немесе бас тарту үшін 0 пернесін басыңыз:");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Дұрыс нөмір енгізіңіз: ", 0, organizations.size());

        if (choice == 0) {
            System.out.println("Таңдау жойылды.");
            return null;
        }

        return organizations.get(choice - 1);
    }

    @Override
    public OrganizationRole selectOrganizationRole(List<OrganizationRole> availableRoles) {
        if (availableRoles.isEmpty()) {
            System.out.println("Қол жетімді рөлдер жоқ.");
            return null;
        }

        System.out.println("\n=== Қол жетімді рөлдер ===");
        for (int i = 0; i < availableRoles.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, availableRoles.get(i));
        }
        System.out.println("========================\n");
        System.out.println("Рөл нөмірін таңдаңыз:");
        int choice = InputValidatorUtil.validateIntegerInput(
                "Дұрыс нөмір енгізіңіз: ", 1, availableRoles.size());

        return availableRoles.get(choice - 1);
    }

    @Override
    public void showOrganizationInfo(List<Organization> organizations) {
        if (organizations.isEmpty()) {
            System.out.println("Сіз ешбір ұйымның мүшесі емессіз.");
            return;
        }

        System.out.println("\n=== Ұйым туралы ақпарат ===");

        for (Organization organization : organizations) {
            System.out.printf("Атауы: %s%n", organization.getName());
            System.out.printf("Сипаттамасы: %s%n", organization.getDescription());
            System.out.println("Қатысушылар:");
            if (organization.getMembers().isEmpty()) {
                System.out.println("  Бұл ұйымға қатысушылар әлі жоқ.");
            } else {
                organization.getMembers().forEach((student, role) -> {
                    System.out.printf("  - %s %s (Рөлі: %s)%n",
                            student.getFirstName(), student.getLastName(), role.name());
                });
            }
            System.out.println("=".repeat(50));
        }
    }

    @Override
    public void showClosedRegistration() {
        System.out.println("Регистрация жабык!");
    }

    @Override
    public void noneAvailableDisciplines() {
        System.out.println("Бос дисциплиналар жок");
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

    @Override
    public void showNoSemesterSelectedMessage() {
        System.out.println("Семестр таңдалмады.");
    }

    @Override
    public void showDiscipline(Discipline discipline) {
        System.out.printf("Пән: %s (Коды: %s, Кредиттер: %d, Семестр: %s, Курстың түрі: %s)%n",
                discipline.getName(), discipline.getCode(), discipline.getCredits(),
                discipline.getSemester(), discipline.getCourseType());
    }

    @Override
    public void showNoDisciplinesAvailableMessage() {
        System.out.println("Пәндер жоқ.");
    }

    @Override
    public void showMarksHeader() {
        System.out.printf("%-10s %-10s %-15s %-10s %-20s%n", "Күні", "Сабақ", "Қатысу", "Баға", "Пікір");
    }

    @Override
    public void showMarkRow(LessonRecord record) {
        System.out.printf("%-10s %-10s %-15s %-10.2f %-20s%n",
                record.getDate(), record.getLesson(), record.getAttendance(), record.getGrade(), record.getComment());
    }

    @Override
    public void showMarksFooter(double totalMarks, int totalPresence, int totalAbsence) {
        System.out.println("------------------------------------------------");
        System.out.printf("Бағалардың жалпы сомасы: %.2f%n", totalMarks);
        System.out.printf("Жалпы қатысу саны: %d%n", totalPresence);
        System.out.printf("Жалпы болмау саны: %d%n", totalAbsence);
        System.out.println("================================================");
    }

    @Override
    public void showNoMarksMessage(Discipline discipline) {
        System.out.println("Пән: "  + discipline.getName() + " бойынша бағалар жоқ!");
    }

    @Override
    public void showSemesterHeader(Semester semester) {
        System.out.printf("\n=== Семестр: %s ===\n", semester.toString());
    }

    @Override
    public void showDisciplineWithoutGrades(Discipline discipline) {
        System.out.printf("Пән: %s (GPA қол жетімді емес; аттестация әлі жабылған жоқ)%n", discipline.getName());
    }

    @Override
    public void showTranscriptRecordWithGrades(Transcript transcript) {
        System.out.printf(
                "Пән: %s | Жалпы баға: %.2f | GPA: %s | Дәстүрлі баға: %s%n",
                transcript.getSubject().getName(),
                transcript.getTotalGrade(),
                transcript.getGpaLetter(),
                transcript.getGpaTraditional()
        );
    }

    @Override
    public Teacher selectTeacher(List<Teacher> teachers) {
        System.out.println("Оқытушыны бағалау үшін таңдаңыз:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.printf("%d. %s %s (Рейтинг: %s)%n", i + 1, teachers.get(i).getFirstName(), teachers.get(i).getLastName(), teachers.get(i).getRating());
        }

        int choice = InputValidatorUtil.validateIntegerInput("Таңдауыңызды енгізіңіз: ", 1, teachers.size());
        return teachers.get(choice - 1);
    }

    @Override
    public int getTeacherRating() {
        System.out.println("Оқытушыға бағаны енгізіңіз (0-100):");
        return InputValidatorUtil.validateIntegerInput("Қате баға. 0 мен 100 арасындағы санды енгізіңіз.", 0, 100);
    }

    @Override
    public void showTeacherRatedMessage(Teacher teacher, int rating) {
        System.out.printf("Сіз %s %s оқытушыны %d ұпаймен бағаладыңыз. Жаңа рейтингі: %s.%n",
                teacher.getFirstName(),
                teacher.getLastName(),
                rating,
                teacher.getRating()
        );
    }

    @Override
    public void showNoTeachersAvailableMessage() {
        System.out.println("Мұғалімдер жоқ.");
    }
}
