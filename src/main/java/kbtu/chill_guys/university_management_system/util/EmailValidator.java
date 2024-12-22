package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.service.AdminService;

import static main.java.kbtu.chill_guys.university_management_system.util.LanguageConstants.*;

public class EmailValidator {
    private final AdminService adminService;

    public EmailValidator(AdminService adminService) {
        this.adminService = adminService;
    }

    public String validateUniqueEmail() {
        Language language = Menu.getInstance().getLanguage();
        while (true) {
            System.out.println(ENTER_EMAIL_MESSAGE.get(language));
            String email = InputValidatorUtil.validateEmailInput(
                    INVALID_EMAIL_FORMAT_MESSAGE.get(language)
            );
            if (adminService.isEmailExists(email)) {
                System.out.println(EMAIL_ALREADY_IN_USE_MESSAGE.get(language));
            } else {
                return email;
            }
        }
    }
}
