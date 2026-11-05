package com.revature.strings;

public class StringFormatting {
    public static void main(String[] args) {
        String name = "Anne";
        int items = 3;
        double total = 58.73;

        System.out.printf("Hi %s, your %d items cost $%.2f%n", name, items, total);
        System.out.println();

        // Use a double '%' to represent a '%' in the output String
        String report = String.format("Score: %d%%", 93); // Score: 93%
        System.out.println(report);
        String equivalent = "Score: %d%%".formatted(93); // Score: 93%
        System.out.println(equivalent);
        System.out.println();

        /*
         * The following uses left align (%-12s) and specified widths (%5 | %10) to format
         * the console output, making it easier to read
         */
        System.out.printf("%-12s %5s %10s%n", "Item", "Qty", "Price");   // headers
        System.out.printf("%-12s %5d %10.2f%n", "Coffee", 2, 9.00);
        System.out.printf("%-12s %5d %10.2f%n", "Bagel", 1, 3.25);

        // The 'repeat' method on Strings repeats the string based on the count arg provided
        System.out.println("-".repeat(29));
        System.out.printf("%-18s %10.2f%n", "Total", 12.25);
    }
}
