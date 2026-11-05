package com.revature.javaBasics.enums;

/*
 * Demonstrates a simple Java enum:
 *   - defining an enum type
 *   - using it in a switch statement
 *   - comparing enum values with '=='
 *   - what gets printed when an enum is printed directly
 */
public class EnumDemo {

    // Simple enum: a fixed set of named constants representing days of the week
    enum Day {
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }

    public static void processDay(Day today) {
        // Using an enum with a switch statement
        switch (today) {
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
            case FRIDAY:
                System.out.println("It's a weekday.");
                break; // exits the switch once matched
            case SATURDAY:
            case SUNDAY:
                System.out.println("It's the weekend!");
                break;
        }

        // Using '==' to compare enum values
        // Enum constants are singletons (only one instance exists per constant),
        // so '==' safely compares them by reference, just like .equals() would.
        if (today == Day.WEDNESDAY) {
            System.out.println("Hump day!");
        }

        Day anotherDay = Day.WEDNESDAY;
        System.out.println("today == anotherDay: " + (today == anotherDay)); // true, same constant

        // Printing an enum directly
        // Enums automatically override toString() to return the constant's name.
        System.out.println("today: " + today); // prints "today: WEDNESDAY"
        System.out.println(Day.FRIDAY);        // prints "FRIDAY"
    }

    public static void main(String[] args) {
        // Assign an enum constant to a variable
        Day today = Day.WEDNESDAY;

        processDay(today);
    }
}
