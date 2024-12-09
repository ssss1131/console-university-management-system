package main.java.kbtu.chill_guys.university_management_system.util;
import java.io.IOException;
import java.util.logging.LogManager;

public class LoggerUtil {

    private LoggerUtil() {
    }

    public static void configureLogging() {
        try {
            LogManager.getLogManager().readConfiguration(
                    LoggerUtil.class.getClassLoader().getResourceAsStream("logging.properties")
            );
        } catch (IOException | NullPointerException e) {
            System.err.println("Something went wrong with logger((( " + e.getMessage());
            e.printStackTrace();
        }
    }
}
