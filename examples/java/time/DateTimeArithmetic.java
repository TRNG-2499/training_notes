package com.revature.time;

import java.time.*;

public class DateTimeArithmetic {
    public static void main(String[] args) {

        // Period: date-based difference (years, months, days)
        LocalDate startDate = LocalDate.of(2023, 4, 15);
        LocalDate today = LocalDate.now();
        Period age = Period.between(startDate, today);
        System.out.println("Time elapsed: " + age.getYears() + " years, "
                + age.getMonths() + " months, " + age.getDays() + " days");

        // Duration: time-based difference (hours, minutes, seconds)
        LocalDateTime taskStart = LocalDateTime.of(2026, 7, 31, 9, 0);
        LocalDateTime taskEnd = LocalDateTime.of(2026, 7, 31, 12, 15);
        Duration taskDuration = Duration.between(taskStart, taskEnd);
        System.out.println("Task ran for: " + taskDuration.toHours() + " hours, "
                + (taskDuration.toMinutes() % 60) + " minutes");

        // plusX() / minusX(): shifting a date forward or backward
        LocalDate dueDate = today.plusDays(30);
        LocalDate reminderDate = dueDate.minusDays(3);
        System.out.println("Due date: " + dueDate);
        System.out.println("Reminder date: " + reminderDate);

        // withX(): setting a specific field directly
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());

        LocalDate feb29 = LocalDate.of(2028, Month.FEBRUARY, 29);
        LocalDate sameDayNextYear = feb29.withYear(feb29.getYear() + 1);
        System.out.println("End of this month: " + endOfMonth);
        System.out.println("Same date next year: " + sameDayNextYear);

        // Remember these dates are immutable - the original object is never changed
        LocalDate original = LocalDate.of(2026, 1, 1);
        LocalDate modified = original.plusMonths(6);
        System.out.println("Original (unchanged): " + original);
        System.out.println("Modified (new object): " + modified);

        // Choosing Period vs Duration: a "1 month" subscription vs a "1 hour" session
        LocalDate subscriptionStart = LocalDate.of(2026, 1, 31);
        LocalDate subscriptionRenewal = subscriptionStart.plus(Period.ofMonths(1));
        System.out.println("Subscription renews: " + subscriptionRenewal); // handles month-length correctly

        Duration sessionLength = Duration.ofHours(1);
        System.out.println("Session length: " + sessionLength.toMinutes() + " minutes");
    }
}
