//package main.java.kbtu.chill_guys.university_management_system.menu;
//
//import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
//import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.dean_command.ApproveNewDisciplineCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.general_command.ShowDisciplinesCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.*;
//import main.java.kbtu.chill_guys.university_management_system.menu.researcher_command.*;
//import main.java.kbtu.chill_guys.university_management_system.menu.student_command.GetStudentRegistrationInfoCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.student_command.RegisterToSemesterCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentDisciplinesCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentMarksCommand;
//import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
//import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.setting_command.SelectLanguageCommand;
//import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.PutMarkCommand;
//
//import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_RESEARCH_ROLES;
//
//public class Main {
//    public static void main(String[] args) {
//        LoggerUtil.configureLogging();
//        Menu menu = Menu.getInstance();
//
//        menu.registerCommand("Login", new LoginCommand());
//        menu.registerCommand("Logout", new LogoutCommand());
//
//        menu.registerCommand("Select language", new SelectLanguageCommand(), UserRole.ADMIN, UserRole.BACHELOR,
//                UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);
//
//        menu.registerCommand("Get logs", new GetLogsCommand(), UserRole.ADMIN);
//        menu.registerCommand("Create new user", new CreateUserCommand(), UserRole.ADMIN);
//        menu.registerCommand("Update user", new UpdateUserCommand(), UserRole.ADMIN);
//        menu.registerCommand("Delete user", new DeleteUserCommand(), UserRole.ADMIN);
//
//        menu.registerCommand("Create journal", new CreateJournalCommand(), UserRole.MANAGER);
//        menu.registerCommand("Delete journal", new DeleteJournalCommand(), UserRole.MANAGER);
////        menu.registerCommand("Publish post", new PublishPostCommand(), UserRole.MANAGER);
//
//        menu.registerCommand("Add news", new AddNewsCommand(), UserRole.MANAGER);
//
//        menu.registerCommand("Create discipline", new RequestToAddNewDisciplineCommand(), UserRole.MANAGER);
//        menu.registerCommand("Finalize approved disciplines", new FinalizeApprovedDisciplinesCommand(), UserRole.MANAGER);
//        menu.registerCommand("Approve new disciplines", new ApproveNewDisciplineCommand(), UserRole.DEAN);
//        menu.registerCommand("Show all disciplines", new ShowDisciplinesCommand(), UserRole.ADMIN, UserRole.BACHELOR, UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);
//        menu.registerCommand("Assign discipline to teacher", new AssignDisciplineToTeacherCommand(), UserRole.MANAGER);
//
//        menu.registerCommand("Open registration", new OpenRegistrationCommand(), UserRole.MANAGER);
//        menu.registerCommand("Close registration", new CloseRegistrationCommand(), UserRole.MANAGER);
//        menu.registerCommand("Get info about registration", new GetInfoAboutRegistrationCommand(), UserRole.MANAGER);
//
//        menu.registerCommand("I want to be researcher!!", new AddResearcherCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Get my research papers", new GetResearchPapersCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Add new research paper", new AddResearchPaperCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Add new research project", new AddResearchProjectCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Get my research projects", new GetResearchProjectsCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Get my research papers sorted", new GetSortedResearchPapersCommand(), ALL_RESEARCH_ROLES);
//        menu.registerCommand("Get all research papers sorted", new GetSortedAllResearchPapersCommand(), ALL_RESEARCH_ROLES);
//
//        menu.registerCommand("Show my disciplines", new ViewStudentDisciplinesCommand(), UserRole.BACHELOR, UserRole.PHD, UserRole.MASTER);
//        menu.registerCommand("View my marks", new ViewStudentMarksCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
//        menu.registerCommand("Register to disciplines", new RegisterToSemesterCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
//        menu.registerCommand("Show registration info", new GetStudentRegistrationInfoCommand(), UserRole.PHD, UserRole.MASTER, UserRole.BACHELOR);
//
//        menu.registerCommand("Put mark", new PutMarkCommand(), UserRole.TEACHER);
//
//        menu.run();
//    }
//}
//
package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.CommandEnum;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.dean_command.ApproveNewDisciplineCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LogoutCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.LoginCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.ShowDisciplinesCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.researcher_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.GetStudentRegistrationInfoCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.RegisterToSemesterCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentDisciplinesCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentMarksCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.ViewStudentsCommand;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.setting_command.SelectLanguageCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.PutMarkCommand;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_RESEARCH_ROLES;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.configureLogging();
        Menu menu = Menu.getInstance();

        menu.registerCommand(CommandEnum.LOGIN, new LoginCommand());
        menu.registerCommand(CommandEnum.LOGOUT, new LogoutCommand());

        menu.registerCommand(CommandEnum.SELECT_LANGUAGE, new SelectLanguageCommand(), UserRole.ADMIN, UserRole.BACHELOR,
                UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);

        menu.registerCommand(CommandEnum.GET_LOGS, new GetLogsCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.CREATE_USER, new CreateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.UPDATE_USER, new UpdateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.DELETE_USER, new DeleteUserCommand(), UserRole.ADMIN);

        menu.registerCommand(CommandEnum.CREATE_JOURNAL, new CreateJournalCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.DELETE_JOURNAL, new DeleteJournalCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.ADD_NEWS, new AddNewsCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.REQUEST_NEW_DISCIPLINE, new RequestToAddNewDisciplineCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.FINALIZE_DISCIPLINES, new FinalizeApprovedDisciplinesCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.APPROVE_NEW_DISCIPLINES, new ApproveNewDisciplineCommand(), UserRole.DEAN);
        menu.registerCommand(CommandEnum.SHOW_DISCIPLINES, new ShowDisciplinesCommand(), UserRole.ADMIN, UserRole.BACHELOR, UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);
        menu.registerCommand(CommandEnum.ASSIGN_DISCIPLINE, new AssignDisciplineToTeacherCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.OPEN_REGISTRATION, new OpenRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.CLOSE_REGISTRATION, new CloseRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.GET_REGISTRATION_INFO, new GetInfoAboutRegistrationCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.ADD_RESEARCHER, new AddResearcherCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_RESEARCH_PAPERS, new GetResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.ADD_RESEARCH_PAPER, new AddResearchPaperCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.ADD_RESEARCH_PROJECT, new AddResearchProjectCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_RESEARCH_PROJECTS, new GetResearchProjectsCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_SORTED_RESEARCH_PAPERS, new GetSortedResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_SORTED_ALL_RESEARCH_PAPERS, new GetSortedAllResearchPapersCommand(), ALL_RESEARCH_ROLES);

        menu.registerCommand(CommandEnum.SHOW_STUDENT_DISCIPLINES, new ViewStudentDisciplinesCommand(), UserRole.BACHELOR, UserRole.PHD, UserRole.MASTER);
        menu.registerCommand(CommandEnum.VIEW_MARKS, new ViewStudentMarksCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(CommandEnum.REGISTER_DISCIPLINES, new RegisterToSemesterCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(CommandEnum.SHOW_REGISTRATION_INFO, new GetStudentRegistrationInfoCommand(), UserRole.PHD, UserRole.MASTER, UserRole.BACHELOR);

        menu.registerCommand(CommandEnum.PUT_MARK, new PutMarkCommand(), UserRole.TEACHER);
        menu.registerCommand(CommandEnum.VIEW_STUDENTS, new ViewStudentsCommand(), UserRole.TEACHER);

        menu.run();
    }
}
