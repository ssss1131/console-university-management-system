package main.java.kbtu.chill_guys.university_management_system.menu.employee_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.MessageService;
import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.List;
import java.util.logging.Logger;

public class ViewMessagesCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ViewMessagesCommand.class.getName());
    private final MessageService messageService = new MessageService();
    private final Menu menu = Menu.getInstance();
    @Override
    public void execute() {
        try {
            User currentUser = menu.getLoggedUser();
            MessageView view = ViewFactory.getMessageView(menu.getLanguage());

            List<String> messages = messageService.viewMessages(currentUser.getEmail());
            view.displayMessages(messages);

            LOGGER.info("Messages viewed by user: " + currentUser.getEmail());
        } catch (Exception e) {
            LOGGER.severe("Error viewing messages: " + e.getMessage());
        }
    }
}