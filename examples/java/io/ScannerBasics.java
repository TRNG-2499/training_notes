package com.revature.io;

import java.util.Scanner;
import java.util.InputMismatchException;

public class ScannerBasics {
    public static void main(String[] args) {

        // Scanner wrapping System.in to read console input
        Scanner scanner = new Scanner(System.in);

        // Reading a String token vs. an entire line
        System.out.print("Enter your first name: ");
        String firstName = scanner.next(); // reads a single token (stops at whitespace)

        // IMPORTANT: next() leaves the trailing newline in the buffer.
        // Without this extra nextLine(), the line below would read an empty string.
        scanner.nextLine(); // consumes the leftover newline

        System.out.print("Enter a sentence about yourself: ");
        String bio = scanner.nextLine(); // reads the full line, including spaces

        System.out.println("Hello, " + firstName + "! You said: " + bio);

        // Reading numeric input safely with hasNextInt()
        System.out.print("Enter your age: ");
        if (scanner.hasNextInt()) {
            int age = scanner.nextInt();
            System.out.println("You are " + age + " years old.");
        } else {
            System.out.println("That doesn't look like a valid age.");
        }

        scanner.nextLine(); // consume leftover newline from nextInt() above

        System.out.print("Enter your favorite color: ");
        String color = scanner.nextLine(); // without the line above, this would be skipped/empty
        System.out.println("Favorite color: " + color);

        // Handling invalid input with a try-catch as an alternative to hasNext() checks
        System.out.print("Enter a whole number: ");
        try {
            int number = scanner.nextInt();
            System.out.println("You entered: " + number);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input -- that wasn't a whole number.");
        }

        // Selectable menu example: Scanner inside a while loop
        // This demonstrates a common real-world pattern -- looping
        // until the user chooses to exit (entering 0), using a
        // switch statement to handle each menu option.
        boolean running = true;

        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Option One");
            System.out.println("2. Option Two");
            System.out.println("3. Option Three");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");

            // Guard against non-numeric input crashing the loop
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a number.");
                scanner.nextLine(); // discard the invalid token so it doesn't loop forever
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You selected option 1");
                    // call methods | create objects | etc...
                    break;
                case 2:
                    System.out.println("You selected option 2");
                    break;
                case 3:
                    System.out.println("You selected option 3");
                    break;
                case 0:
                    System.out.println("Exiting menu...");
                    running = false; // this is what ends the while loop
                    break;
                default:
                    System.out.println("Select an appropriate option, 1, 2 or 3");
            }
        }

        // Always close the Scanner when done to release the resource
        scanner.close();
    }
}
