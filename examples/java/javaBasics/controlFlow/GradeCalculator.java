/**
 * @author William Gentry
 */

package com.revature.javaBasics.controlFlow;

public class GradeCalculator {

	// notice the use of varargs (...) here!
	public static double calculateAverage(String... grades) {
		// Holder variable
		double sum = 0.0;
		
		// Enhanced for loop
		for (String s : grades) {
			sum += new Double(s);
		}
		
		return (sum / grades.length);
	}
	
	public static void main(String... args) {
		System.out.println("The arguments you provided to the command line:");
		for (String s : args) {
			System.out.println(s);
		}
		
		System.out.println(GradeCalculator.calculateAverage(args));
	}
}