package main.java.kbtu.chill_guys.university_management_system.menu.employee_command;

import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.service.MessageService;
import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.logging.Logger;

public class SendMessageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SendMessageCommand.class.getName());
    private final MessageService messageService = new MessageService();
    private final Menu menu = Menu.getInstance();

    @Override
    public void execute() {
        try {
            User currentUser = menu.getLoggedUser();
            MessageView view = ViewFactory.getMessageView(menu.getLanguage());
            String receiverEmail = view.getReceiverEmail();
            String messageContent = view.getMessageContent();

            messageService.sendMessage(currentUser.getEmail(), receiverEmail, messageContent);

            view.displayMessageSent();

            LOGGER.info("Message sent successfully by user: " + currentUser.getEmail());
        } catch (Exception e) {
            LOGGER.severe("Error sending message: " + e.getMessage());
        }
    }
}