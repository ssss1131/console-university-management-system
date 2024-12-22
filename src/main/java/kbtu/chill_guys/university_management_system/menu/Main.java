package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.dean_command.ApproveNewDisciplineCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.researcher_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.GetStudentRegistrationInfoCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.RegisterToSemesterCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ShowInfoAboutDiplomaProjectCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentDisciplinesCommand;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.setting_command.SelectLanguageCommand;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_RESEARCH_ROLES;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_ROLES;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.configureLogging();
        Menu menu = Menu.getInstance();

        menu.registerCommand("Login", new LoginCommand());
        menu.registerCommand("Logout", new LogoutCommand());

        menu.registerCommand("Select language", new SelectLanguageCommand(), UserRole.ADMIN, UserRole.BACHELOR,
                UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);

        menu.registerCommand("Get logs", new GetLogsCommand(), UserRole.ADMIN);
        menu.registerCommand("Create new user", new CreateUserCommand(), UserRole.ADMIN);
        menu.registerCommand("Update user", new UpdateUserCommand(), UserRole.ADMIN);
        menu.registerCommand("Delete user", new DeleteUserCommand(), UserRole.ADMIN);

        menu.registerCommand("Create journal", new CreateJournalCommand(), UserRole.MANAGER);
        menu.registerCommand("Delete journal", new DeleteJournalCommand(), UserRole.MANAGER);
//        menu.registerCommand("Publish post", new PublishPostCommand(), UserRole.MANAGER);

        menu.registerCommand("Add news", new AddNewsCommand(), UserRole.MANAGER);

        menu.registerCommand("create discipline", new RequestToAddNewDisciplineCommand(), UserRole.MANAGER);
        menu.registerCommand("finalize approved disciplines", new FinalizeApprovedDisciplinesCommand(), UserRole.MANAGER);
        menu.registerCommand("approve new disciplines", new ApproveNewDisciplineCommand(), UserRole.DEAN);
        menu.registerCommand("show my disciplines", new ViewStudentDisciplinesCommand(), UserRole.BACHELOR, UserRole.PHD, UserRole.MASTER);
        menu.registerCommand("show all disciplines", new ShowDisciplinesCommand(), ALL_ROLES);

        menu.registerCommand("open registration", new OpenRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand("close registration", new CloseRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand("register to disciplines", new RegisterToSemesterCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand("get info about registration", new GetInfoAboutRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand("show registration info", new GetStudentRegistrationInfoCommand(), UserRole.PHD, UserRole.MASTER, UserRole.BACHELOR);
        menu.registerCommand("assign supervisor to student", new AssignSupervisorCommand(), UserRole.MANAGER);

        menu.registerCommand("i want to be researcher!!", new AddResearcherCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("get my research papers", new GetResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("add new research paper", new AddResearchPaperCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("add new research project", new AddResearchProjectCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("get my research projects", new GetResearchProjectsCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("get my research papers sorted", new GetSortedResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("get all research papers sorted", new GetSortedAllResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("get top researcher by year", new GetTopResearcherByYearCommand(), ALL_ROLES);
        menu.registerCommand("get top researcher by school", new GetTopCitedResearcherBySchoolCommand(), ALL_ROLES);
        menu.registerCommand("show my research paper in format", new GetResearchPaperInFormatCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand("show and edit my diploma project", new ShowInfoAboutDiplomaProjectCommand(), UserRole.MASTER, UserRole.PHD);
        menu.registerCommand("view journals", new ViewJournalCommand(), ALL_ROLES);

        menu.run();
    }
}

