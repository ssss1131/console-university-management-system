package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.List;

public class GetLogsCommand implements Command {
    private AdminController controller = new AdminController();
    private AdminView view = new AdminView();

    @Override
    public void execute() {
        LogPeriod period = view.getLogPeriod();
        List<String> logs = controller.getLogs(period);
        view.displayLogs(logs);
    }
}

