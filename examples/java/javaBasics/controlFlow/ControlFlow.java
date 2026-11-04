/**
 * @author William Gentry
 */

package com.revature.javaBasics.controlFlow;

public class ControlFlow {

	// In order to access instance methods or variables, you must have an instantiated object
	// To access static methods or variables, however, do not require an instantiated object 
	
	public void ifElseIfElseExample(int a) {
		if (a < 0) {
			System.out.println("The number is negative");
		} else if (a > 0) {
			System.out.println("The number is positive");
		} else {
			System.out.println("The number is zero");
		}
	}
	
	public void switchExample(String season) {
		switch(season) {
			// Every one of these case statements can be thought of as an individual
			// "else if" in an if-statement
			case "Summer":
				System.out.println("Let's go to the pool/lake!");
				break;
			case "Fall":
				System.out.println("Trick-or-treat!");
				break;
			case "Winter":
				System.out.println("It's snowing!!!");
				break;
			case "Spring":
				System.out.println("Spraaang Break");
				break;
			default:
				System.out.println("Season not recognized");
		}
	}
	
	public static void main(String[] args) {
		// Instantiated our ControlFlow object
		ControlFlow cf = new ControlFlow();
		
		// Invoke the method - showing each lane of execution
		cf.ifElseIfElseExample(10);
		cf.ifElseIfElseExample(-10);
		cf.ifElseIfElseExample(0);
		
		cf.switchExample("Spring");
		cf.switchExample("Summer");
		cf.switchExample("William");
	}
	
}











