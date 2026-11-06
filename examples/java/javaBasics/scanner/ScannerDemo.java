package com.revature.javaBasics.scanner;

/*
 * The Scanner class exists within the java.util package
 * of the JRE
 */
import java.util.Scanner;

public class ScannerDemo {
    // Creating a static scanner instead of creating the scanner within the main method.
    public static Scanner app_Scanner = new Scanner(System.in);

    /*
     * The Scanner class allows you to read data from
     * a location specified (in our case, System.in)
     * and return data to be stored in a variable.
     *
     * This is similar to reading data from a prompt()
     * function in Javascript
     *
     * Or the Input() function in Python
     */
    public static void main(String[] args) {
        System.out.println("::: START OF APPLICATION :::");

        Scanner scan = new Scanner(System.in);
        System.out.println("Give me an int:");
        // nextInt() - Integers
        // nextDouble() - Doubles (decimals)
        // nextFloat() - Floats (decimals)
        // next() - String input until a whitespace/break/space

        // nextLine() - String input until a linebreak/newline character

        /*
         * A common pitfall to watch out for:
         *   nextLine() reads new line characters, so if you use newLine()
         *   after any other 'next' method of the Scanner, you will have to
         *   call it again, as the first call will just eat any stray newline
         *   inputs from the user (when they press 'Return/Enter')
         *
         * nextInt() - "5"
         * nextLine() - "\n"
         * nextLine() - "new input"...
         */

        int in = scan.nextInt();
        System.out.println("You gave me: " + in);

        System.out.println("::: END OF APPLICATION :::");

        /*
         * Some classes are resource intensive, and as such
         * java wants you to 'close' these resources. Closing
         * a resource means that you will stop using it entirely.
         * Note, if you close a resource, it CANNOT BE REOPENED!
         * do not close your scanner until you are completely
         * done with it!
         */
        scan.close();

		Scanner scan_2 = new Scanner(System.in); // This will throw an error - the System.in (Input Stream) was closed!
		double dbl = scan_2.nextDouble();
    }
}
