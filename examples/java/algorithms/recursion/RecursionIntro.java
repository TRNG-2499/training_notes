package com.revature.algorithms.recursion;

public class RecursionIntro {
    public static void main(String[] args) {
        System.out.println("Iteration:");
        printNumbersWithLoop(10);

        System.out.println("Recursion:");
        printNumbersWithRecursion(10);
    }

    // How do we iterate with a loop? - we've seen this before!
    public static void printNumbersWithLoop(int n) {
        // Guard - if the number given is less than 0, we won't want
        // an infinite loop
        if (n<= 0)
            System.out.println(0);

        for (int i = n; i > 0; i--)
            System.out.println(i);
    }

    // This showcases the exact same idea (iteration of numbers) using recursion
    // This works by calling the 'printNumbersWithRecursion' method, inside of itself
    // anytime a method/function calls itself, that is recursion!
    public static int printNumbersWithRecursion(int n) {
        // This is for our base case! when the number is 0 (or if
        // we were give a negative number), we just print 0 and return
        // back to the stack.
        if (n <= 0) {
            return 0;
        }

        System.out.println(n);

        return printNumbersWithRecursion(--n);
    }
}
