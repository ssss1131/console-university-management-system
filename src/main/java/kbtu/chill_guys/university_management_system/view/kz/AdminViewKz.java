package main.java.kbtu.chill_guys.university_management_system.view.kz;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.MasterProgram;

import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.PhdProgram;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Specialization;
import main.java.kbtu.chill_guys.university_management_system.enumeration.academic.TeachingDegree;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.ManagerType;
import main.java.kbtu.chill_guys.university_management_system.enumeration.organization.School;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.*;
import static main.java.kbtu.chill_guys.university_management_system.util.EnumSelectionUtil.selectEnum;
import static main.java.kbtu.chill_guys.university_management_system.util.InputValidatorUtil.*;

public class AdminViewKz implements AdminView {
    @Override
    public Map<String, Object> getUserInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Пайдаланушының рөлін енгізіңіз:");
        UserRole role = selectEnum(UserRole.class);
        data.put(USER_ROLE_ATTRIBUTE, role);

        System.out.println("Құпия сөзді енгізіңіз:");
        data.put(PASSWORD_ATTRIBUTE, validateNonEmptyInput("Құпия сөз бос болмауы керек!"));

        System.out.println("Атыңызды енгізіңіз:");
        data.put(FIRSTNAME_ATTRIBUTE, validateNonEmptyInput("Атыңыз бос болмауы керек!"));

        System.out.println("Тегіңізді енгізіңіз:");
        data.put(LASTNAME_ATTRIBUTE, validateNonEmptyInput("Тегіңіз бос болмауы керек!"));

        switch (role) {
            case BACHELOR, MASTER, PHD -> handleStudentInput(data);
            case TEACHER -> handleTeacherInput(data);
            case MANAGER -> handleManagerInput(data);
            case DEAN -> handleDeanInput(data);
            case PROFESSOR -> handleProfessorInput(data);
            case RESEARCH_SUPERVISOR -> handleResearchSupervisorInput(data);
        }

        return data;
    }

    private void handleResearchSupervisorInput(Map<String, Object> data) {
        System.out.println("Жалақыны енгізіңіз:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Жалақы теріс болмауы керек", 0, Integer.MAX_VALUE));
    }

    private void handleProfessorInput(Map<String, Object> data) {
        System.out.println("Жалақыны енгізіңіз:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Жалақы теріс болмауы керек", 0, Integer.MAX_VALUE));

        System.out.println("Рейтингті енгізіңіз:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Рейтинг оң бүтін сан болуы керек", 0, 100));

        System.out.println("Мектепті енгізіңіз:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));
    }

    @Override
    public void displayUserCreated(User user) {
        System.out.println("Пайдаланушы сәтті құрылды!");
        System.out.println(user);
    }

    private void handleStudentInput(Map<String, Object> data) {
        System.out.println("Мектепті таңдаңыз:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Тіркеу күнін енгізіңіз (жжжж-аа-кк):");
        data.put(ENROLLMENT_DATE_ATTRIBUTE, validateDateInput("Күні дұрыс емес форматта. Жарамды күн енгізіңіз."));

        data.put(CREDITS_ATTRIBUTE, 0);

        System.out.println("Оқу ұзақтығын енгізіңіз (жылдар):");
        data.put(STUDY_DURATION_ATTRIBUTE, validateIntegerInput("Оқу ұзақтығы оң бүтін сан болуы керек!", 0, Integer.MAX_VALUE));

        UserRole role = (UserRole) data.get(USER_ROLE_ATTRIBUTE);
        switch (role) {
            case MASTER -> {
                System.out.println("Магистрлік бағдарламаны таңдаңыз:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("PhD бағдарламасын таңдаңыз:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(PhdProgram.class));
            }
            case BACHELOR -> {
                System.out.println("Бакалавриат бағдарламасын таңдаңыз:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(Specialization.class));
            }
        }
    }

    private void handleTeacherInput(Map<String, Object> data) {
        System.out.println("Жалақыны енгізіңіз:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Жалақы теріс болмауы керек", 0, Integer.MAX_VALUE));

        System.out.println("Рейтингті енгізіңіз:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Рейтинг оң бүтін сан болуы керек", 0, 100));

        System.out.println("Мектепті енгізіңіз:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Оқытушының дәрежесін енгізіңіз:");
        data.put(TEACHING_DEGREE_ATTRIBUTE, selectEnum(TeachingDegree.class));
    }

    private void handleManagerInput(Map<String, Object> data) {
        System.out.println("Жалақыны енгізіңіз:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Жалақы теріс болмауы керек", 0, Integer.MAX_VALUE));

        System.out.println("Менеджер түрін енгізіңіз:");
        data.put(MANAGER_TYPE_ATTRIBUTE, selectEnum(ManagerType.class));
    }

    private void handleDeanInput(Map<String, Object> data) {
        System.out.println("Жалақыны енгізіңіз:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Жалақы теріс болмауы керек", 0, Integer.MAX_VALUE));
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public UUID getUserIdForDeletion() {
        System.out.println("Жою үшін пайдаланушының ID-ін енгізіңіз:");
        return InputValidatorUtil.validateUUIDInput("UUID форматы дұрыс емес. Қайтадан көріңіз.");
    }

    @Override
    public LogPeriod getLogPeriod() {
        System.out.println("Журнал кезеңін таңдаңыз:");
        return selectEnum(LogPeriod.class);
    }

    @Override
    public void displayLogs(List<String> logs) {
        if (logs.isEmpty()) {
            System.out.println("Таңдалған кезең үшін журналдар табылмады.");
        } else {
            System.out.println("Таңдалған кезең үшін журналдар:");
            logs.forEach(System.out::println);
        }
    }

    @Override
    public void displayUserAlreadyExists() {
        System.out.println("Пошта бірегей болуы керек. Қайталап көріңіз!");
    }

    @Override
    public void displayAllUsers(List<User> users) {
        System.out.println("=== Қолданушылар ===");
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. %s %s (Электрондық пошта: %s, Рөлі: %s)%n",
                    i + 1, users.get(i).getFirstName(), users.get(i).getLastName(), users.get(i).getEmail(), users.get(i).getRole());
        }
    }

    @Override
    public int getUserIndexForDeletion(int maxIndex) {
        return InputValidatorUtil.validateIntegerInput(
                "Жою үшін қолданушының нөмірін енгізіңіз:",
                1,
                maxIndex
        ) - 1;
    }

    @Override
    public boolean confirmDeletion(User user) {
        System.out.printf("Қолданушыны жоюға сенімдісіз бе? %s %s (Электрондық пошта: %s)? (иә/жоқ): ",
                user.getFirstName(), user.getLastName(), user.getEmail());
        String input = InputValidatorUtil.validateNonEmptyInput("Өтінеміз, 'иә' немесе 'жоқ' деп жазыңыз.");
        return input.equalsIgnoreCase("иә");
    }

    @Override
    public void displayNoUsersToDelete() {
        System.out.println("Жоюға қолданушылар жоқ!");
    }

    @Override
    public void displayUserDeletionCancelled() {
        System.out.println("Қолданушыны жою тоқтатылды.");
    }

    @Override
    public void displayUserDeletedSuccessfully() {
        System.out.println("Қолданушы сәтті жойылды.");
    }

    @Override
    public Map<String, Object> getFieldsForUpdate(User user) {
        System.out.println("Пайдаланушыны жаңарту үшін өрістерді таңдаңыз: ");
        System.out.println("1. Аты");
        System.out.println("2. Тегі");
        System.out.println("3. Электрондық пошта");
        System.out.println("Сандарды үтір арқылы енгізіңіз (мысалы, 1,3):");

        String input = InputValidatorUtil.validateNonEmptyInput("Қате енгізу. Қайта көріңіз.");
        String[] fields = input.split(",");

        Map<String, Object> updatedFields = new HashMap<>();
        for (String field : fields) {
            switch (field.trim()) {
                case "1" -> {
                    System.out.println("Жаңа атын енгізіңіз:");
                    updatedFields.put("firstName", validateNonEmptyInput("Аты бос болмауы керек."));
                }
                case "2" -> {
                    System.out.println("Жаңа тегін енгізіңіз:");
                    updatedFields.put("lastName", validateNonEmptyInput("Тегі бос болмауы керек."));
                }
                case "3" -> {
                    System.out.println("Жаңа электрондық поштаны енгізіңіз:");
                    updatedFields.put("email", validateEmailInput("Электрондық пошта пішімі қате. Қайта көріңіз."));
                }
                default -> System.out.println("Өрістің қате нөмірі: " + field.trim());
            }
        }
        return updatedFields;
    }

    @Override
    public void displayNoUsersForUpdate() {
        System.out.println("Жаңартуға қолжетімді пайдаланушылар жоқ!");
    }

    @Override
    public void displayUserUpdatedSuccessfully() {
        System.out.println("Пайдаланушы сәтті жаңартылды.");
    }
}
