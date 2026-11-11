package com.revature.exceptions;

import java.util.Scanner;

/*
 * A 'try-with-resources' block allows you to scope a closeable
 * resource to a try-block, and allows Java to automatically close
 * the resource when it has completed the try-block logic.
 *
 * Syntax:
 *
 * try (ResourceType variableName = initializer) {
 *    // try-block-code...
 * }
 */
public class TryWithResources {
    public static void main(String[] args) {
        // Try-with-resources block using Scanner
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            System.out.printf("Hello, %s! You are %d years old.%n", name, age);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } // Here - exiting the block the Scanner will be closed


        // Below showcases the 'manual' way of closing a resource.
        // You should avoid this, because it is prone to human-error
        // i.e. forgetting to call the close() method...
        Scanner scan = new Scanner(System.in);
        try {
            scan.nextLine();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (scan != null) {
                scan.close();
            }
        }

    }
}
