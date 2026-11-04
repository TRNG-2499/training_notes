/**
 * @author Wezley Singleton
 */

package com.revature.javaBasics.varargs;

public class VarArgsDriver {
	
	public static void main(String... args) {
		
		int[] myIntArray = {9, 8, 7, 6, 5};
		
		display("Printing out the Array", null);
		display("String with no Integers provided"); // the variable argument is optional
		display("Providing Ints in a comma-separated list", 2,3,4,5,78,52); //another way to write varargs
		
		int[][] my2dArray = { {1, 2}, {3, 4} };
		show("2x2 2D array", my2dArray);
	}
	
	// The variable argument MUST be last in the parameter list! Otherwise you get a compiler error!
	// with varargs we are saying we want to have potentially an array (or not), with as many values
	// as the caller needs
	public static void display(String strValue, int... arr) {
		
		System.out.println(strValue);

		if (arr == null || arr.length == 0) {
			System.out.println("Log the fact that it is null");
			return;
		}
		
		// Using a for-each (enhanced for-loop) is the easiest way to iterate through an array
		for(int val : arr) {
			System.out.println(val);
		}
	}
	
	/*
	 * We can make our variable arguments as many dimensions as desired, just add a single set of square 
	 * brackets and the ellipses to pass in a 2d array.
	 */
	public static void show(String message, int[]... twoDimArr) {
		System.out.println(message);
		// nested for-each loops to iterate through the positions of the matrix
		for(int[] arr : twoDimArr) {
			for(int num : arr) {
				System.out.println(num);
			}
		}
	}

}
