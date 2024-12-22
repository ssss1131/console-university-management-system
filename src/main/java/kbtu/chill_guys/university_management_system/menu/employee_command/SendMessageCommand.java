package main.java.kbtu.chill_guys.university_management_system.menu.employee_command;

import main.java.kbtu.chill_guys.university_management_system.controller.MessageController;
import main.java.kbtu.chill_guys.university_management_system.menu.Command;
import main.java.kbtu.chill_guys.university_management_system.menu.Menu;
import main.java.kbtu.chill_guys.university_management_system.model.User;
import main.java.kbtu.chill_guys.university_management_system.model.factory.ViewFactory;
import main.java.kbtu.chill_guys.university_management_system.view.MessageView;

import java.util.logging.Logger;

public class SendMessageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SendMessageCommand.class.getName());
    private final MessageController messageController;
    private final Menu menu;

    public SendMessageCommand() {
        this.messageController = new MessageController();
        this.menu = Menu.getInstance();
    }

    @Override
    public void execute() {
        try {
            User currentUser = menu.getLoggedUser();
            if (currentUser == null) {
                System.out.println("You must be logged in to send messages");
                return;
            }

            // Get view based on current language
            MessageView view = ViewFactory.getMessageView(menu.getLanguage());

            // Get message details from user
            String receiverEmail = view.getReceiverEmail();
            String messageContent = view.getMessageContent();

            // Send message
            messageController.sendMessage(currentUser.getEmail(), receiverEmail, messageContent);

            // Show success message
            view.displayMessageSent();

            LOGGER.info("Message sent successfully by user: " + currentUser.getEmail());
        } catch (Exception e) {
            LOGGER.severe("Error sending message: " + e.getMessage());
            System.out.println("Failed to send message: " + e.getMessage());
        }
    }
}