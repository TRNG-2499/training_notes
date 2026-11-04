/**
 * @author William Gentry
 */

package com.revature.javaBasics.controlFlow;

public class LoopsExample {
	
	String[] languages = new String[] {"Java", "JavaScript", "TypeScript", "SQL", "Bash"};
	
	public void forLoopExample() {
		// When using a for loop, we use three things:
		// - Looping variable
		// - Boolean Condition
		// - Increment
		for (int i = 0; i < languages.length; i++) {
			// System.out.println appends the newline character everytime
			// System.out.println(languages[i]);
		
			// System.out.print does not append the newline character
			System.out.print(languages[i] + " ");
		}
	}
	
	
	public void whileLoopExample() {
		// Declaring the looping variable
		int i = 0;
		
				// boolean expression
		while (i < languages.length) {
			System.out.println(languages[i]);
			// increment
			i++;
		}
	}
	
	
	public void doWhileLoopExample() {
		// Declare the looping variable
		int i = 0;
		do {
			// Inside these brackets are where the repeated code is executed
			System.out.println(languages[i]);
			
			// Increment the looping variable
			i++;
			
				// Boolean expression
		} while (i < languages.length);
	}
	
	
	public static void printEvens() {
		for (int i = 1; i <= 100; i++) {
			if (i % 2 == 1) {
				// continue is used to skip to the next iteration
				continue;
			}
			System.out.println(i);
		}
	}
	
	
	public static void main(String[] pickles) {
		LoopsExample example = new LoopsExample();
		// go through each example:
		example.forLoopExample();
		//example.whileLoopExample();
		//example.doWhileLoopExample();
		//LoopsExample.printEvens();
	}
}









