package main.java.kbtu.chill_guys.university_management_system.util;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.dean_command.ApproveNewDisciplineCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.employee_command.SendMessageCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.employee_command.ViewMessagesCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.researcher_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.setting_command.SelectLanguageCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.CloseAttestationCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.PutMarkCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.ViewStudentsCommand;

import static main.java.kbtu.chill_guys.university_management_system.enumeration.util.CommandEnum.*;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_ROLES;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_RESEARCH_ROLES;

public final class CommandRegistrar {

    private CommandRegistrar() {
    }

    public static void registerAllCommands(Menu menu) {
        registerGeneralCommands(menu);
        registerAdminCommands(menu);
        registerManagerCommands(menu);
        registerResearcherCommands(menu);
        registerStudentCommands(menu);
        registerTeacherCommands(menu);
        registerDeanCommands(menu);
    }

    private static void registerGeneralCommands(Menu menu) {
        menu.registerCommand(LOGIN, new LoginCommand());
        menu.registerCommand(LOGOUT, new LogoutCommand());
        menu.registerCommand(SELECT_LANGUAGE, new SelectLanguageCommand(), ALL_ROLES);
        menu.registerCommand(VIEW_JOURNAL, new ViewJournalCommand(), ALL_ROLES);
        menu.registerCommand(GET_TOP_RESEARCHER_BY_YEAR, new GetTopResearcherByYearCommand(), ALL_ROLES);
        menu.registerCommand(GET_TOP_RESEARCHER_BY_SCHOOL, new GetTopCitedResearcherBySchoolCommand(), ALL_ROLES);
        menu.registerCommand(SHOW_DISCIPLINES, new ShowDisciplinesCommand(),ALL_ROLES);
    }

    private static void registerAdminCommands(Menu menu) {
        menu.registerCommand(GET_LOGS, new GetLogsCommand(), UserRole.ADMIN);
        menu.registerCommand(CREATE_USER, new CreateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(UPDATE_USER, new UpdateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(DELETE_USER, new DeleteUserCommand(), UserRole.ADMIN);
    }

    private static void registerManagerCommands(Menu menu) {
        menu.registerCommand(CREATE_JOURNAL, new CreateJournalCommand(), UserRole.MANAGER);
        menu.registerCommand(DELETE_JOURNAL, new DeleteJournalCommand(), UserRole.MANAGER);
        menu.registerCommand(ADD_NEWS, new AddNewsCommand(), UserRole.MANAGER);
        menu.registerCommand(REQUEST_NEW_DISCIPLINE, new RequestToAddNewDisciplineCommand(), UserRole.MANAGER);
        menu.registerCommand(FINALIZE_DISCIPLINES, new FinalizeApprovedDisciplinesCommand(), UserRole.MANAGER);
        menu.registerCommand(OPEN_REGISTRATION, new OpenRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CLOSE_REGISTRATION, new CloseRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(GET_REGISTRATION_INFO, new GetInfoAboutRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(ASSIGN_SUPERVISOR, new AssignSupervisorCommand(), UserRole.MANAGER);
    }

    private static void registerResearcherCommands(Menu menu) {
        menu.registerCommand(ADD_RESEARCHER, new AddResearcherCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(GET_RESEARCH_PAPERS, new GetResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(ADD_RESEARCH_PAPER, new AddResearchPaperCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(ADD_RESEARCH_PROJECT, new AddResearchProjectCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(GET_RESEARCH_PROJECTS, new GetResearchProjectsCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(GET_SORTED_RESEARCH_PAPERS, new GetSortedResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(GET_SORTED_ALL_RESEARCH_PAPERS, new GetSortedAllResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(SHOW_RESEARCH_PAPER_IN_FORMAT, new GetResearchPaperInFormatCommand(), ALL_RESEARCH_ROLES);
    }

    private static void registerStudentCommands(Menu menu) {
        menu.registerCommand(SHOW_STUDENT_DISCIPLINES, new ViewStudentDisciplinesCommand(), UserRole.BACHELOR, UserRole.PHD, UserRole.MASTER);
        menu.registerCommand(VIEW_MARKS, new ViewStudentMarksCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(REGISTER_DISCIPLINES, new RegisterToSemesterCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(SHOW_REGISTRATION_INFO, new GetStudentRegistrationInfoCommand(), UserRole.PHD, UserRole.MASTER, UserRole.BACHELOR);
        menu.registerCommand(SHOW_AND_EDIT_DIPLOMA_PROJECT, new ShowInfoAboutDiplomaProjectCommand(), UserRole.MASTER, UserRole.PHD);
    }

    private static void registerTeacherCommands(Menu menu) {
        menu.registerCommand(PUT_MARK, new PutMarkCommand(), UserRole.TEACHER);
        menu.registerCommand(VIEW_STUDENTS, new ViewStudentsCommand(), UserRole.TEACHER);
        menu.registerCommand(VIEW_MESSAGES, new ViewMessagesCommand(), UserRole.TEACHER, UserRole.MANAGER, UserRole.DEAN);
        menu.registerCommand(SEND_MESSAGE, new SendMessageCommand(), UserRole.TEACHER, UserRole.MANAGER, UserRole.DEAN);
        menu.registerCommand(ASSIGN_DISCIPLINE, new AssignDisciplineToTeacherCommand(), UserRole.MANAGER);
        menu.registerCommand(CLOSE_ATTESTATION, new CloseAttestationCommand(), UserRole.TEACHER);
    }

    private static void registerDeanCommands(Menu menu){
        menu.registerCommand(APPROVE_NEW_DISCIPLINES, new ApproveNewDisciplineCommand(), UserRole.DEAN);
    }
}
