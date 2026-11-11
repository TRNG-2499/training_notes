package com.revature.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormatterBasics {
    public static void main(String[] args) {

        LocalDate today = LocalDate.of(2026, 7, 21);

        // Using a predefined ISO-8601 formatter - good for machine-readable output
        String isoFormatted = today.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("ISO format: " + isoFormatted); // 2026-07-21

        // Using a custom pattern - good for human-facing display
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String customFormatted = today.format(customFormatter);
        System.out.println("Custom format: " + customFormatted); // 07/21/2026

        // A more descriptive pattern, e.g. for a UI header
        DateTimeFormatter longFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        System.out.println("Long format: " + today.format(longFormatter)); // Tuesday, July 21, 2026

        // Formatting a LocalDateTime - date AND time components together
        LocalDateTime meeting = LocalDateTime.of(2026, 7, 21, 14, 30);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
        System.out.println("Meeting time: " + meeting.format(dateTimeFormatter)); // 07/21/2026 14:30

        // Parsing: converting a String back into a LocalDate using a matching formatter
        String userInput = "12/25/2026";
        LocalDate parsedDate = LocalDate.parse(userInput, customFormatter);
        System.out.println("Parsed date: " + parsedDate); // 2026-12-25

        // Handling invalid input safely with try-catch
        String badInput = "not-a-real-date";
        try {
            LocalDate.parse(badInput, customFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Failed to parse date: " + badInput + " -- " + e.getMessage());
        }
    }
}
