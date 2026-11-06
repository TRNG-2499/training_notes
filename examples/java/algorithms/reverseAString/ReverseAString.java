package com.revature.algorithms.reverseAString;

import java.util.Scanner;

public class ReverseAString {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Give me a String to reverse");
		String input = scan.nextLine();

		System.out.println( reverse(input) );

		System.out.println("Give me another:");
		System.out.println( reverse(scan.nextLine()) );
	}

	// Look how optimal we can be when we leverage java libraries!
	public static String optimal(String input) {
//        StringBuilder build = new StringBuilder(input);
//        String reversed = build.reverse().toString();
		return (input == null || input.isEmpty()) ? "" : new StringBuilder(input).reverse().toString();
	}

	// Reversing a String
	public static String reverse(String input) {
		// If there string input is null, or has no characters, exit immediately with empty string
		if (input == null || input.isEmpty()) return "";

		String output = "";

		/*
		 * lets just look at the input string, in reverse order
		 * and append that to our output string
		 */
		for (int index = input.length() - 1; index >= 0; index--) {
			output += input.charAt(index);
		}

		return output;
	}

	// Bonus: Alternative String Reverse implementation
	// notice that this one has the 'guard' as part of the loop's body
	// we check if the input has a character at some index, then
	// once we reach the end, we move backwards from there
	public static String alternativeReverse(String input) {
		String toBeReturned = "";
		int index = 0;
		while (true) {
			try {
				input.charAt(index);
				index++;
			} catch (IndexOutOfBoundsException e) {
				index--;
				for (int i = -1; index > i; index--) {
					toBeReturned += input.charAt(index);
				}
				break;
			}
		}
		return toBeReturned;
	}


}
