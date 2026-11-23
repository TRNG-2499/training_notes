package com.revature.modernjava.switchexpressions;

public class SwitchProblems {

    /*
      * Switch statements have fall-through logic, meaning that
      * forgetting a 'break' can cause bugs
     */
    public double fallThrough(String ageGroup) {
        double price;
        switch(ageGroup) {
            case "CHILD":
                price= 8.00;
                // Bug: missing Break, so execution falls through to the next case
            case "ADULT":
                price = 14.00;
                break;
            case "SENIOR":
                price = 10.00;
                break;
            default:
                price = 14.00;
        }
        return price;
    }

    // Arrow syntax does not have fall-through logic
    public static double ticketPrice(String ageGroup) {
        double price;
        switch (ageGroup) {
            case "CHILD" -> price = 8.00; // no break needed - no fall-through possible
            case "ADULT" -> price = 14.00;
            case "SENIOR" -> price = 10.00;
            default -> price = 14.00;
        }
        return price;
    }

    /*
     * Variables declared in a switch statement share a scope, meaning that you
     * cannot reuse the same variable name in different cases
     */
    public void shareScope(String language) {
        switch (language) {
            case "EN": {
                String message = "Hello";
                System.out.println(message);
                break;
            }
            case "ES": {
                String message = "Hola";       // COMPILE ERROR: variable message is already defined
                System.out.println(message);
                break;
            }
        }
    }

    /*
     * By default each case has it's own scope (syntactically we include curly
     * braces as well with switch expressions
     */
    public static void greet(String language) {
        switch (language) {
            case "EN" -> {
                String message = "Hello"; // only exists inside this block
                System.out.println(message);
            }
            case "ES" -> {
                String message = "Hola"; // same name is fine, different block
                System.out.println(message);
            }
            default -> System.out.println("Hi");
        }
    }


    /*
     * When returning data from a switch statement, you must follow a clunky
     * and fairly verbose structure:
     *      declare un-initialized variable
     *      assign a value in each case & include a default assignment
     *      return the variable
     */
    public double verboseAssignments(String state) {
        double rate; // declare a variable with no value yet

        // Each case must assign a value to 'rate', including a 'default' case
        switch (state) {
            case "CO":
                rate = 0.029;
                break;
            case "TX":
                rate = 0.0625;
                break;
            case "CA":
                double base = 0.0725;
                double districtAvg = 0.01;
                rate = base + districtAvg;
                break;
            default:
                rate = 0.05;
        }
        return rate;
    }

    /*
      * Using Arrow syntax, we can combine a return keyword with a switch
      * that produces the value directly;
      *
      * the 'yield' keyword can handle multi-line cases
     */
    public static double taxRate(String state) {
        return switch (state) {
            case "CO" -> 0.029;
            case "TX" -> 0.0625;
            case "CA" -> {
                double base = 0.0725;
                double districtAvg = 0.01;
                yield base + districtAvg;      // yield supplies this case's value
            }
            default -> 0.05;
        };
    }


    /*
     * Switch statements do not have Completeness checks, so in instances
     * such as using an Enum, if you do not account for a value, the compiler
     * doesn't see any issues
     */
    public enum TrafficLight { RED, YELLOW, GREEN}

    public String noCompleteness(TrafficLight light) {
        String action = "Unknown";
        switch (light) {
            case RED:
                action = "Stop";
                break;
            // No case for Yellow was defined!
            case GREEN:
                action = "Go";
                break;
        }
        return action;
    }

    // Arrow syntax has a completeness check.
    public String action(TrafficLight light) {
        return switch(light) {
            case RED -> "Stop";
            case YELLOW -> "Slow down";
            case GREEN -> "Go";
        };
    }
}
