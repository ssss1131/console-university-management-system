package main.java.kbtu.chill_guys.university_management_system.view.ru;

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

public class AdminViewRu implements AdminView {
    @Override
    public Map<String, Object> getUserInput() {
        Map<String, Object> data = new HashMap<>();

        System.out.println("Введите роль пользователя:");
        UserRole role = selectEnum(UserRole.class);
        data.put(USER_ROLE_ATTRIBUTE, role);

        System.out.println("Введите email:");
        data.put(EMAIL_ATTRIBUTE, validateEmailInput("Неправильный формат email. Попробуйте еще раз."));

        System.out.println("Введите пароль:");
        data.put(PASSWORD_ATTRIBUTE, validateNonEmptyInput("Пароль не может быть пустым!"));

        System.out.println("Введите имя:");
        data.put(FIRSTNAME_ATTRIBUTE, validateNonEmptyInput("Имя не может быть пустым!"));

        System.out.println("Введите фамилию:");
        data.put(LASTNAME_ATTRIBUTE, validateNonEmptyInput("Фамилия не может быть пустой!"));

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

    private void handleProfessorInput(Map<String, Object> data) {
        System.out.println("Введите зарплату:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Зарплата не может быть отрицательной", 0, Integer.MAX_VALUE));

        System.out.println("Введите рейтинг:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Рейтинг должен быть положительным целым числом", 0, 100));

        System.out.println("Введите школу:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));
    }

    @Override
    public void displayUserCreated(User user) {
        System.out.println("Пользователь создан успешно!");
        System.out.println(user);
    }

    private void handleStudentInput(Map<String, Object> data) {
        System.out.println("Выберите школу:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Введите дату регистрации (гггг-мм-дд):");
        data.put(ENROLLMENT_DATE_ATTRIBUTE, validateDateInput("Неверный формат даты. Пожалуйста, введите действительную дату."));

        data.put(CREDITS_ATTRIBUTE, 0);

        System.out.println("Введите продолжительность обучения (годы):");
        data.put(STUDY_DURATION_ATTRIBUTE, validateIntegerInput("Продолжительность обучения должна быть положительным целым числом!", 0, Integer.MAX_VALUE));

        UserRole role = (UserRole) data.get(USER_ROLE_ATTRIBUTE);
        switch (role) {
            case MASTER -> {
                System.out.println("Выберите магистерскую программу:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(MasterProgram.class));
            }
            case PHD -> {
                System.out.println("Выберите программу PhD:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(PhdProgram.class));
            }
            case BACHELOR -> {
                System.out.println("Выберите программу бакалавриата:");
                data.put(PROGRAM_ATTRIBUTE, selectEnum(Specialization.class));
            }
        }

    }

    private void handleTeacherInput(Map<String, Object> data) {
        System.out.println("Введите зарплату:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Зарплата не может быть отрицательной", 0, Integer.MAX_VALUE));

        System.out.println("Введите рейтинг:");
        data.put(RATING_ATTRIBUTE, validateIntegerInput("Рейтинг должен быть положительным целым числом", 0, 100));

        System.out.println("Введите школу:");
        data.put(SCHOOL_ATTRIBUTE, selectEnum(School.class));

        System.out.println("Введите степень преподавателя:");
        data.put(TEACHING_DEGREE_ATTRIBUTE, selectEnum(TeachingDegree.class));
    }

    private void handleManagerInput(Map<String, Object> data) {
        System.out.println("Введите зарплату:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Зарплата не может быть отрицательной", 0, Integer.MAX_VALUE));

        System.out.println("Введите тип менеджера:");
        data.put(MANAGER_TYPE_ATTRIBUTE, selectEnum(ManagerType.class));
    }

    private void handleDeanInput(Map<String, Object> data) {
        System.out.println("Введите зарплату:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Зарплата не может быть отрицательной", 0, Integer.MAX_VALUE));
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public UUID getUserIdForDeletion() {
        System.out.println("Введите ID пользователя для удаления:");
        return InputValidatorUtil.validateUUIDInput("Неверный формат UUID. Попробуйте снова.");
    }

    @Override
    public LogPeriod getLogPeriod() {
        System.out.println("Выберите период логов:");
        return selectEnum(LogPeriod.class);
    }

    @Override
    public void displayLogs(List<String> logs) {
        if (logs.isEmpty()) {
            System.out.println("Логи за выбранный период не найдены.");
        } else {
            System.out.println("Логи за выбранный период:");
            logs.forEach(System.out::println);
        }
    }

    @Override
    public void handleResearchSupervisorInput(Map<String, Object> data) {
        System.out.println("Введите зарплату:");
        data.put(SALARY_ATTRIBUTE, validateIntegerInput("Зарплата не может быть отрицательной", 0, Integer.MAX_VALUE));
    }
}

