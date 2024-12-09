package main.java.kbtu.chill_guys.university_management_system.menu.admin_command;

import main.java.kbtu.chill_guys.university_management_system.controller.AdminController;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.view.AdminView;

import java.util.List;
import java.util.Vector;

public class GetLogsCommand implements Command {
    private AdminController controller;
    private AdminView view;

    public GetLogsCommand(AdminController controller, AdminView view) {
        this.controller = controller;
        this.view = view;
    }

    @Override
    public void execute() {
        LogPeriod period = view.getLogPeriod();
        List<String> logs = controller.getLogs(period);
        view.displayLogs(logs);
    }
}

