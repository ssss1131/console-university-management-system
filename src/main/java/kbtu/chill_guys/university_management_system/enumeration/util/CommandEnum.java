package main.java.kbtu.chill_guys.university_management_system.enumeration.util;

public enum CommandEnum {
    LOGIN("Login", "Войти", "Кіру"),
    LOGOUT("Logout", "Выйти", "Шығу"),
    SELECT_LANGUAGE("Select language", "Выбрать язык", "Тілді таңдау"),

    SEND_MESSAGE("Send message", "Отправить сообщение", "Хабарлама жіберу"),
    VIEW_MESSAGES("View messages", "Просмотр сообщений", "Хабарларды көру"),

    GET_LOGS("Get logs", "Получить логи", "Журналдарды алу"),
    CREATE_USER("Create new user", "Создать нового пользователя", "Жаңа қолданушы жасау"),
    UPDATE_USER("Update user", "Обновить пользователя", "Қолданушыны жаңарту"),
    DELETE_USER("Delete user", "Удалить пользователя", "Қолданушыны жою"),

    APPROVE_NEW_DISCIPLINES("Approve new disciplines", "Утвердить новые дисциплины", "Жаңа пәндерді бекіту"),

    CREATE_JOURNAL("Create journal", "Создать журнал", "Журнал жасау"),
    DELETE_JOURNAL("Delete journal", "Удалить журнал", "Журналды жою"),
    VIEW_JOURNAL("View journals", "Просмотр журналов", "Журналдарды қарау"),

    ADD_NEWS("Add news", "Добавить новости", "Жаңалық қосу"),
    REQUEST_NEW_DISCIPLINE("Create discipline", "Создать дисциплину", "Пән жасау"),
    FINALIZE_DISCIPLINES("Finalize approved disciplines", "Завершить утвержденные дисциплины", "Бекітілген пәндерді аяқтау"),
    ASSIGN_DISCIPLINE("Assign discipline to teacher", "Назначить дисциплину преподавателю", "Мұғалімге пән беру"),
    OPEN_REGISTRATION("Open registration", "Открыть регистрацию", "Тіркеуді ашу"),
    CLOSE_REGISTRATION("Close registration", "Закрыть регистрацию", "Тіркеуді жабу"),
    GET_REGISTRATION_INFO("Get info about registration", "Получить информацию о регистрации", "Тіркеу туралы ақпарат алу"),

    SHOW_STUDENT_DISCIPLINES("Show my disciplines", "Показать мои дисциплины", "Менің пәндерімді көрсету"),
    VIEW_MARKS("View my marks", "Посмотреть мои оценки", "Менің бағаларымды көру"),
    REGISTER_DISCIPLINES("Register to disciplines", "Зарегистрироваться на дисциплины", "Пәндерге тіркелу"),
    SHOW_REGISTRATION_INFO("Show registration info", "Показать информацию о регистрации", "Тіркеу туралы ақпаратты көрсету"),

    PUT_MARK("Put mark", "Поставить оценку", "Баға қою"),
    VIEW_STUDENTS("View info about students", "Просмотр информации о студентах", "Студенттер туралы ақпаратты қарау"),
    CLOSE_ATTESTATION("Close attestation", "Закрыть аттестацию", "Аттестаттауды жабу"),

    ADD_RESEARCHER("I want to be researcher!!", "Я хочу стать исследователем!!", "Мен зерттеуші болғым келеді!!"),
    GET_RESEARCH_PAPERS("Get my research papers", "Получить мои исследовательские работы", "Менің зерттеу жұмыстарымды алу"),
    ADD_RESEARCH_PAPER("Add new research paper", "Добавить новую исследовательскую работу", "Жаңа зерттеу жұмысын қосу"),
    ADD_RESEARCH_PROJECT("Add new research project", "Добавить новый исследовательский проект", "Жаңа зерттеу жобасын қосу"),
    GET_RESEARCH_PROJECTS("Get my research projects", "Получить мои исследовательские проекты", "Менің зерттеу жобаларымды алу"),
    GET_SORTED_RESEARCH_PAPERS("Get my research papers sorted", "Получить мои исследования отсортированными", "Менің зерттеу жұмыстарымды сұрыптау"),
    GET_SORTED_ALL_RESEARCH_PAPERS("Get all research papers sorted", "Получить все исследования отсортированными", "Барлық зерттеу жұмыстарын сұрыптау"),
    ASSIGN_SUPERVISOR("Assign supervisor to student", "Назначить руководителя студенту", "Студентке ғылыми жетекшіні тағайындаңыз"),
    GET_TOP_RESEARCHER_BY_YEAR(
            "Get top researcher by year",
            "Получить лучшего исследователя года",
            "Жылдың үздік зерттеушісін алу"
    ),
    GET_TOP_RESEARCHER_BY_SCHOOL(
            "Get top researcher by school",
            "Получить лучшего исследователя по школе",
            "Мектеп бойынша үздік зерттеушіні алу"
    ),
    SHOW_RESEARCH_PAPER_IN_FORMAT(
            "Show my research paper in format",
            "Показать мою исследовательскую работу в формате",
            "Зерттеу жұмысымды форматта көрсету"
    ),
    SHOW_AND_EDIT_DIPLOMA_PROJECT(
            "Show and edit my diploma project",
            "Показать и редактировать мой дипломный проект",
            "Дипломдық жобамды көрсету және өңдеу"
    ),

    SHOW_DISCIPLINES("Show all disciplines", "Показать все дисциплины", "Барлық пәндерді көрсету");

    private final String en;
    private final String ru;
    private final String kz;

    CommandEnum(String en, String ru, String kz) {
        this.en = en;
        this.ru = ru;
        this.kz = kz;
    }

    public String getTranslation(Language language) {
        return switch (language) {
            case RU -> ru;
            case KZ -> kz;
            default -> en;
        };
    }
}
