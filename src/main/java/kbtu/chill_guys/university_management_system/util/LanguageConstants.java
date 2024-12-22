package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;

import java.util.Map;

public class LanguageConstants {

    private LanguageConstants(){
    }

    public static final Map<Language, String> UNKNOWN_COMMAND_MESSAGE = Map.of(
            Language.EN, "Unknown command or insufficient permissions.",
            Language.RU, "Неизвестная команда или недостаточно прав.",
            Language.KZ, "Белгісіз команда немесе жеткіліксіз құқықтар."
    );

    public static final Map<Language, String> AVAILABLE_COMMANDS_MESSAGE = Map.of(
            Language.EN, "Available commands:",
            Language.RU, "Доступные команды:",
            Language.KZ, "Қол жетімді командалар:"
    );

    public static final Map<Language, String> SELECT_LANGUAGE_MESSAGE = Map.of(
            Language.EN, "Select a language:",
            Language.RU, "Выберите язык:",
            Language.KZ, "Тілді таңдаңыз:"
    );

    public static final Map<Language, String> LANGUAGE_EN = Map.of(
            Language.EN, "English",
            Language.RU, "Английский",
            Language.KZ, "Ағылшын"
    );

    public static final Map<Language, String> LANGUAGE_RU = Map.of(
            Language.EN, "Russian",
            Language.RU, "Русский",
            Language.KZ, "Орыс"
    );

    public static final Map<Language, String> LANGUAGE_KZ = Map.of(
            Language.EN, "Kazakh",
            Language.RU, "Казахский",
            Language.KZ, "Қазақ"
    );

    public static final Map<Language, String> INVALID_LANGUAGE_CHOICE = Map.of(
            Language.EN, "Invalid choice. Defaulting to English.",
            Language.RU, "Неверный выбор. Язык установлен на Английский.",
            Language.KZ, "Қате таңдау. Тіл Әңғылшын тіліне орнатылды."
    );

    public static final Map<Language, String> LANGUAGE_SET_MESSAGE = Map.of(
            Language.EN, "Language set to English.",
            Language.RU, "Язык установлен на русский.",
            Language.KZ, "Тіл Қазақша орнатылды."
    );

    public static final Map<Language, String> ENTER_DATE_MESSAGE = Map.of(
            Language.EN, "Enter date (yyyy-MM-dd):",
            Language.RU, "Введите дату (yyyy-MM-dd):",
            Language.KZ, "Күні енгізіңіз (yyyy-MM-dd):"
    );

    public static final Map<Language, String> DATE_AFTER_MESSAGE = Map.of(
            Language.EN, "The date must be after ",
            Language.RU, "Дата должна быть позже ",
            Language.KZ, "Күні кейін болуы керек "
    );

    public static final Map<Language, String> INVALID_NUMBER_MESSAGE = Map.of(
            Language.EN, "Invalid input. Please enter a valid number.",
            Language.RU, "Неверный ввод. Пожалуйста, введите корректное число.",
            Language.KZ, "Қате енгізу. Дұрыс сан енгізіңіз."
    );

    public static final Map<Language, String> CHOOSE_COMMAND_MESSAGE = Map.of(
       Language.EN, "Choose command: ",
       Language.RU, "Выберите команду: ",
       Language.KZ, "Команданы танданыз: "
    ) ;

    public static final Map<Language, String> INVALID_OPTION_MESSAGE = Map.of(
            Language.EN, "Invalid number. Please enter a valid option.",
            Language.RU, "Неверное число. Пожалуйста, выберите правильный вариант.",
            Language.KZ, "Қате сан. Дұрыс опцияны таңдаңыз."
    );

    public static final Map<Language, String> ENTER_EMAIL_MESSAGE = Map.of(
            Language.EN, "Enter email:",
            Language.RU, "Введите email:",
            Language.KZ, "Email енгізіңіз:"
    );

    public static final Map<Language, String> INVALID_EMAIL_FORMAT_MESSAGE = Map.of(
            Language.EN, "Invalid email format. Please try again.",
            Language.RU, "Неверный формат email. Пожалуйста, попробуйте снова.",
            Language.KZ, "Қате email форматы. Қайта енгізіңіз."
    );

    public static final Map<Language, String> EMAIL_ALREADY_IN_USE_MESSAGE = Map.of(
            Language.EN, "The email is already in use. Please enter a different email.",
            Language.RU, "Этот email уже используется. Пожалуйста, введите другой email.",
            Language.KZ, "Бұл email қазірдің өзінде қолданыста. Басқа email енгізіңіз."
    );

    public static final Map<Language, String> ENTER_OPTION_MESSAGE = Map.of(
            Language.EN, "Enter your choice:",
            Language.RU, "Введите ваш выбор:",
            Language.KZ, "Таңдауыңызды енгізіңіз:"
    );

    public static final Map<Language, String> ENTER_MULTIPLE_OPTIONS_MESSAGE = Map.of(
            Language.EN, "Enter numbers separated by commas (e.g., 1,2,3):",
            Language.RU, "Введите числа через запятую (например, 1,2,3):",
            Language.KZ, "Сандарды үтір арқылы енгізіңіз (мысалы, 1,2,3):"
    );

    public static final Map<Language, String> NO_SELECTION_MADE_MESSAGE = Map.of(
            Language.EN, "No selections made.",
            Language.RU, "Выбор не сделан.",
            Language.KZ, "Ештеңе таңдалмады."
    );

    public static final Map<Language, String> INVALID_INPUT_MESSAGE = Map.of(
            Language.EN, "Invalid input. Please enter numbers separated by commas.",
            Language.RU, "Неверный ввод. Пожалуйста, введите числа через запятую.",
            Language.KZ, "Қате енгізу. Сандарды үтір арқылы енгізіңіз."
    );

    public static final Map<Language, String> INVALID_NUMBER_SKIP_MESSAGE = Map.of(
            Language.EN, "Invalid number: %s. Skipping.",
            Language.RU, "Неверное число: %s. Пропускаем.",
            Language.KZ, "Қате сан: %s. Өтіп кетеміз."
    );


}
