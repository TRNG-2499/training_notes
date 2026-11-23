package com.revature.modernjava.switchexpressions;

/*
 * The basic syntax of a switch using Arrow syntax is as follows:
 *
 * switch (variable_to_evaluate) {
 *      case value(s) -> expression;
 *      case value(s) -> { block_of_statements };
 *      case value(s) -> throw ExceptionType
 * }
 */
public class ArrowSyntax {

    // This example showcases basic Arrow-syntax for a switch statement
    public void arrowSyntax(String letter) {
        if (letter.length() != 1)  throw new IllegalArgumentException("Invalid input");

        switch (letter.toUpperCase()) {
            case "A", "E", "I", "O", "U" -> System.out.println("A vowel");
            case "Y" -> System.out.println("Sometimes a Vowel");
            default -> System.out.println("A consonant");
        }
    }

    // Arrow Syntax supports multiple values for a single case.
    public enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
    public static void printSchedule(Day day) {
        switch (day) {
            case SATURDAY, SUNDAY -> System.out.println("Weekend: office closed");
            case FRIDAY -> System.out.println("Casual Friday");
            default -> System.out.println("Regular workday");
        }
    }

    /*
     * Expressions in Switch Arrow Syntax produce values which can be returned
     * directly from the switch statement
     *
     * A typical format is:
     *   return switch(value_to_evaluate) {
     *      case value(s) -> expression_to_return;
     *      etc...
     *   }
     */
    public static String describeStatus(int httpStatus) {
        return switch (httpStatus) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 400 -> "Bad Request";
            case 401, 403 -> "Not Authorized";
            case 404 -> "Not Found";
            case 500 -> "Server Error";
            default -> "Unknown status: " + httpStatus;
        };
    }

    public static void OtherSwitchExpressionAssignment(int value) {
        // Switch expressions can be assigned to variables
        String output = switch (value) {
            case 1 -> "one";
            case 2 -> "two";
            default -> "default is required";
        };

        String month = "jan";

        // You can also pass a switch as an argument to another method
        // Note that this isn't very common
        daysInMonth(
                switch(month) {
                    case "jan" -> 1;
                    case "feb" -> 2;
                    case "mar" -> 3;
                    default -> 12;
                }
        );

    }

    // Arrow-Syntax switch statements can throw an Exception in the signature
    public static int daysInMonth(int month) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28; // ignoring leap years
            default -> throw new IllegalArgumentException("Invalid month: " + month);
            // Note: a 'throw' finishes a case, so it cannot be used in the same
            // block with 'yield' to produce a value as well (see - 'Yield.java' for more details)
        };
    }
}
