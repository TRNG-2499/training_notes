package com.revature.time;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.Month;

public class DateTimeBasics {
    public static void main(String[] args) {

        // LocalDate: date only, no time or time zone
        LocalDate today = LocalDate.now();
        LocalDate specificDate = LocalDate.of(2026, Month.JULY, 21); // year, month, day
        System.out.println("Today: " + today);
        System.out.println("Specific date: " + specificDate);

        // LocalTime: time only, no date or time zone
        LocalTime now = LocalTime.now();
        LocalTime specificTime = LocalTime.of(14, 30); // 2:30 PM
        System.out.println("Current time: " + now);
        System.out.println("Specific time: " + specificTime);

        // LocalDateTime: combines LocalDate + LocalTime, still no time zone
        LocalDateTime meeting = LocalDateTime.of(specificDate, specificTime);
        System.out.println("Meeting scheduled: " + meeting);

        // Instant: a precise moment on the timeline (used for timestamps)
        Instant timestamp = Instant.now();
        System.out.println("Current timestamp (UTC): " + timestamp);

        // Demonstrating immutability:
        // plusDays() does NOT modify specificDate -- it returns a new object
        LocalDate nextWeek = specificDate.plusDays(7);
        System.out.println("Original date is unchanged: " + specificDate);
        System.out.println("New date returned: " + nextWeek);
    }
}
