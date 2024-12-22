package main.java.kbtu.chill_guys.university_management_system.view;

import java.util.List;

public interface MessageView {
    String getReceiverEmail();  // Получить почту получателя
    String getMessageContent(); // Получить содержание сообщения
    void displayMessages(List<String> messages); // Отобразить сообщения
    void displayMessageSent(); // Сообщить об успешной отправке
    void displayError(String errorMessage); // Отобразить сообщение об ошибке
}
