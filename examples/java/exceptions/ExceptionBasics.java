package com.revature.exceptions;

public class ExceptionBasics {
    public static void main(String[] args) {
        System.out.println("Start of application");
        String[] words = new String[] {"hello", "world"};
        try {
            int i = 1/0;
            // If the JVM does not detect a matching exception, the application
            // will terminate.
            System.out.println(words[1]);
        } catch (ArithmeticException e) {
            System.out.println("Diving by 0 is a no-no");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You tried to access an index that doesn't exist");
        }


        System.out.println("End of application");
    }
}
