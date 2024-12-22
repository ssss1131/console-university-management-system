package main.java.kbtu.chill_guys.university_management_system.menu.teacher_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.employee.Teacher;
import main.java.kbtu.chill_guys.university_management_system.view.TeacherView;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;

public class ViewTeacherRatingCommand implements Command {
    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        TeacherView view = ViewFactory.getTeacherView(currentLanguage);

        Teacher teacher = (Teacher) Menu.getInstance().getLoggedUser();
        if (teacher != null) {
            view.showTeacherRating(teacher);
        } else {
            System.out.println("Only teachers can access this command.");
        }
    }
}
