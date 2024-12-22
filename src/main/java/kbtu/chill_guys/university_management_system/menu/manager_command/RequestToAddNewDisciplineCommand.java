package main.java.kbtu.chill_guys.university_management_system.menu.manager_command;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.Language;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.academic.Discipline;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.DisciplineService;
import main.java.kbtu.chill_guys.university_management_system.storage.DisciplineStatusStorage;
import main.java.kbtu.chill_guys.university_management_system.view.ManagerView;

import java.util.List;

import static main.java.kbtu.chill_guys.university_management_system.enumeration.academic.Status.PENDING_APPROVAL;

public class RequestToAddNewDisciplineCommand implements Command {

    private final DisciplineStatusStorage disciplineStatusStorage = DisciplineStatusStorage.getInstance();
    private final DisciplineService service = new DisciplineService();

    @Override
    public void execute() {
        Language currentLanguage = Menu.getInstance().getLanguage();
        ManagerView view = ViewFactory.getManagerView(currentLanguage);
        List<Discipline> allDisciplines = service.getAllDisciplines();
        boolean isNotUniqueCode = true;
        String code = "";
        while (isNotUniqueCode){
            code = view.getCode();
            if(!service.isUniqueCode(code)){
                view.showAlreadyExistingMessage();
            } else {
                isNotUniqueCode = false;
            }
        }
        Discipline discipline = view.getNewDisciplineInput(code, allDisciplines);
        disciplineStatusStorage.setStatus(discipline, PENDING_APPROVAL);
        view.showRequestSentConfirmation();
    }
}
