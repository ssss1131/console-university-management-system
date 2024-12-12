package main.java.kbtu.chill_guys.university_management_system.repository;

import main.java.kbtu.chill_guys.university_management_system.enumeration.util.LogPeriod;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static main.java.kbtu.chill_guys.university_management_system.util.Constant.LOG_FILE_PATH;

public class LogRepository {
    private static final DateTimeFormatter LOG_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<String> getLogs(LogPeriod period) {
        List<String> filteredLogs = new ArrayList<>();
        LocalDate startDate = calculateStartDate(period);

        try (BufferedReader reader = Files.newBufferedReader(LOG_FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (isLogInPeriod(line, startDate)) {
                    filteredLogs.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }

        return filteredLogs;
    }

    private LocalDate calculateStartDate(LogPeriod period) {
        LocalDate now = LocalDate.now();
        switch (period) {
            case DAY:
                return now.minusDays(1);
            case WEEK:
                return now.minusWeeks(1);
            case MONTH:
                return now.minusMonths(1);
            default:
                throw new IllegalArgumentException("Unsupported period: " + period);
        }
    }

    private boolean isLogInPeriod(String logLine, LocalDate startDate) {
        try {
            String datePart = logLine.substring(0, 19);
            LocalDate logDate = LocalDate.parse(datePart, LOG_DATE_FORMATTER);
            return !logDate.isBefore(startDate);
        } catch (Exception e) {
            return false;
        }
    }
}
