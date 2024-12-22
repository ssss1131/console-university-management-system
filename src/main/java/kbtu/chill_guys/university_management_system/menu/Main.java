package main.java.kbtu.chill_guys.university_management_system.menu;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.CommandEnum;
import main.java.kbtu.chill_guys.university_management_system.enumeration.util.UserRole;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.DeleteUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.GetLogsCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.dean_command.ApproveNewDisciplineCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.employee_command.SendMessageCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.employee_command.ViewMessagesCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.general_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.CreateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.admin_command.UpdateUserCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.manager_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.researcher_command.*;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.GetStudentRegistrationInfoCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.RegisterToSemesterCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ShowInfoAboutDiplomaProjectCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentDisciplinesCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.student_command.ViewStudentMarksCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.ViewStudentsCommand;
import main.java.kbtu.chill_guys.university_management_system.util.LoggerUtil;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.CreateJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.journal_command.DeleteJournalCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.setting_command.SelectLanguageCommand;
import main.java.kbtu.chill_guys.university_management_system.menu.teacher_command.PutMarkCommand;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_RESEARCH_ROLES;
import static main.java.kbtu.chill_guys.university_management_system.util.Constant.ALL_ROLES;

public class Main {
    public static void main(String[] args) {
        LoggerUtil.configureLogging();
        Menu menu = Menu.getInstance();

        menu.registerCommand(CommandEnum.LOGIN, new LoginCommand());
        menu.registerCommand(CommandEnum.LOGOUT, new LogoutCommand());

        menu.registerCommand(CommandEnum.SELECT_LANGUAGE, new SelectLanguageCommand(), ALL_ROLES);

        menu.registerCommand(CommandEnum.GET_LOGS, new GetLogsCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.CREATE_USER, new CreateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.UPDATE_USER, new UpdateUserCommand(), UserRole.ADMIN);
        menu.registerCommand(CommandEnum.DELETE_USER, new DeleteUserCommand(), UserRole.ADMIN);

        menu.registerCommand(CommandEnum.CREATE_JOURNAL, new CreateJournalCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.DELETE_JOURNAL, new DeleteJournalCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.VIEW_JOURNAL, new ViewJournalCommand(), ALL_ROLES);

        menu.registerCommand(CommandEnum.ADD_NEWS, new AddNewsCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.REQUEST_NEW_DISCIPLINE, new RequestToAddNewDisciplineCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.FINALIZE_DISCIPLINES, new FinalizeApprovedDisciplinesCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.APPROVE_NEW_DISCIPLINES, new ApproveNewDisciplineCommand(), UserRole.DEAN);
        menu.registerCommand(CommandEnum.SHOW_DISCIPLINES, new ShowDisciplinesCommand(), UserRole.ADMIN, UserRole.BACHELOR, UserRole.DEAN, UserRole.MANAGER, UserRole.MASTER, UserRole.PHD, UserRole.TEACHER, UserRole.PROFESSOR);
        menu.registerCommand(CommandEnum.ASSIGN_DISCIPLINE, new AssignDisciplineToTeacherCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.OPEN_REGISTRATION, new OpenRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.CLOSE_REGISTRATION, new CloseRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.GET_REGISTRATION_INFO, new GetInfoAboutRegistrationCommand(), UserRole.MANAGER);
        menu.registerCommand(CommandEnum.ASSIGN_SUPERVISOR, new AssignSupervisorCommand(), UserRole.MANAGER);

        menu.registerCommand(CommandEnum.ADD_RESEARCHER, new AddResearcherCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_RESEARCH_PAPERS, new GetResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.ADD_RESEARCH_PAPER, new AddResearchPaperCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.ADD_RESEARCH_PROJECT, new AddResearchProjectCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_RESEARCH_PROJECTS, new GetResearchProjectsCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_SORTED_RESEARCH_PAPERS, new GetSortedResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_SORTED_ALL_RESEARCH_PAPERS, new GetSortedAllResearchPapersCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.GET_TOP_RESEARCHER_BY_YEAR, new GetTopResearcherByYearCommand(), ALL_ROLES);
        menu.registerCommand(CommandEnum.GET_TOP_RESEARCHER_BY_SCHOOL, new GetTopCitedResearcherBySchoolCommand(), ALL_ROLES);
        menu.registerCommand(CommandEnum.SHOW_RESEARCH_PAPER_IN_FORMAT, new GetResearchPaperInFormatCommand(), ALL_RESEARCH_ROLES);
        menu.registerCommand(CommandEnum.SHOW_AND_EDIT_DIPLOMA_PROJECT, new ShowInfoAboutDiplomaProjectCommand(), UserRole.MASTER, UserRole.PHD);

        menu.registerCommand(CommandEnum.SHOW_STUDENT_DISCIPLINES, new ViewStudentDisciplinesCommand(), UserRole.BACHELOR, UserRole.PHD, UserRole.MASTER);
        menu.registerCommand(CommandEnum.VIEW_MARKS, new ViewStudentMarksCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(CommandEnum.REGISTER_DISCIPLINES, new RegisterToSemesterCommand(), UserRole.BACHELOR, UserRole.MASTER, UserRole.PHD);
        menu.registerCommand(CommandEnum.SHOW_REGISTRATION_INFO, new GetStudentRegistrationInfoCommand(), UserRole.PHD, UserRole.MASTER, UserRole.BACHELOR);

        menu.registerCommand(CommandEnum.PUT_MARK, new PutMarkCommand(), UserRole.TEACHER);
        menu.registerCommand(CommandEnum.VIEW_STUDENTS, new ViewStudentsCommand(), UserRole.TEACHER);

        menu.registerCommand(CommandEnum.VIEW_MESSAGES, new ViewMessagesCommand(), UserRole.TEACHER, UserRole.MANAGER, UserRole.DEAN);
        menu.registerCommand(CommandEnum.SEND_MESSAGE, new SendMessageCommand(), UserRole.TEACHER, UserRole.MANAGER, UserRole.DEAN);

        menu.run();
    }
}
