package com.revature.modernjava.patternmatching;

import java.util.*;

public class PatternMatching {
    /*
     * In the old syntax, we must create a variable and cast it as a separate
     * step, after checking the type using instance of
     */
    public static String oldSyntax(Object obj) {
        if (obj instanceof String) {
            String text = (String) obj;
            return "Text with " + text.length() + " characters";
        } else if (obj instanceof Integer) {
            Integer number = (Integer) obj;
            return "Number: " + (number * 2) + " when doubled";
        }
        return "Unknown type";
    }

    /*
     * The new syntax allows you to cast and assign a variable (binding variable)
     * in the same line of the instanceof type check
     */
    public static String newSyntax(Object obj) {
        if (obj instanceof String text) {
            return "Text with " + text.length() + " characters";
        } else if (obj instanceof Integer number) {
            return "Number: " + (number * 2) + " when doubled";
        }
        return "Unknown type";
    }


    /*
     * Using instanceof with negation is a pattern called a 'Guard Clause'.
     *
     * The pattern works by checking if some input matches an expected
     * type, and exiting the function early if it does not.
     *
     * if the Guard is passed, then we have access to the pattern-matched
     * variable in the rest of the method
     */
    public static String formatPrice(Object input) {
        /*
         * This is our Guard clause. We handle the "wrong type"
         * case first and leave early if the type isn't what we
         * expect
         */
        if (!(input instanceof Double price)) {
            throw new IllegalArgumentException("Invalid price");
        }

        // if we did not return, then the binding variable 'price' has
        // a value available for use in the remainder of the method
        if (price < 0) {
            return "Price cannot be negative";
        }
        return String.format("$%,.2f", price);
    }

    /*
     * Pattern matching can be a powerful tool to process loosely typed data.
     *
     * In this example, imagine that our 'map' is data read from a JSON or other config
     * file, and parsed into a Map<String, Object>
     */
    public static void checkSettings(Map<String, Object> settings) {
        for (var entry : settings.entrySet()) {
            Object value = entry.getValue();
            String display;

            if (value instanceof Boolean flag) {
                display = flag ? "enabled" : "disabled";
            } else if (value instanceof Integer n && n >= 1000) {
                display = String.format("%,d", n);
            } else if (value instanceof List<?> list) {
                display = list.size() + " item(s)";
            } else {
                display = String.valueOf(value);
            }

            System.out.println(entry.getKey() + ": " + display);
        }
    }
}
