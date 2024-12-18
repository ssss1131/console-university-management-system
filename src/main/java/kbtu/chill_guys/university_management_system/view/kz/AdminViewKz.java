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
import main.java.kbtu.chill_guys.university_management_system.model.student.Organization;
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

        System.out.println("Email енгізіңіз:");
        data.put(EMAIL_ATTRIBUTE, validateEmailInput("Email форматы дұрыс емес. Қайтадан көріңіз."));

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
        }

        return data;
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

        System.out.printf("Ұйым атауын енгізіңіз (немесе өткізіп жіберу үшін %s енгізіңіз): ", CANCEL_INPUT);
        String input = validateNonEmptyInput("Ұйым туралы мәліметтерді дұрыс енгізу.");
        if (!input.equalsIgnoreCase(CANCEL_INPUT)) {
            data.put(ORGANIZATION_ATTRIBUTE, getOrganizationInput());
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

    private Organization getOrganizationInput() {
        System.out.println("Ұйымның атауын енгізіңіз:");
        String name = InputValidatorUtil.validateNonEmptyInput("Ұйым атауы бос болмауы керек");

        System.out.println("Ұйымның сипаттамасын енгізіңіз:");
        String description = InputValidatorUtil.validateNonEmptyInput("Ұйымның сипаттамасы бос болмауы керек");

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);

        return organization;
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
}
