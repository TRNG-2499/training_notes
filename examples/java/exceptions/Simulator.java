package com.revature.exceptions;

public class Simulator {

	public static void someOtherMethod() {}
	/*
	 * When a declared method has a 'throws' declaration,
	 * it is used to consolidate how many try/catch blocks
	 * are needed throughout your code. In this case, any
	 * method which has the 'throws' declaration must 
	 * eventually be handled within a try/catch block, as 
	 * shown below:
	 */
	public static void main(String[] args) {
		CustomMathOperations math = new CustomMathOperations();

		int userInput = 0;

		loop: while (userInput <= 5) {
			// some code...
			// get UserInput here...
			switch (userInput) {
				case 1:
					// something...
					try {
						// Scanner to read user input for data to pass to method...
						math.sumOddNumbers(userInput, 0);
					} catch (EvenNumberException e) {
						// How should our application respond to this error getting thrown?
						someOtherMethod();
						// print stack trace or other messaging?
						System.out.println("Please enter numbers in the correct format...");
						userInput = 0;
					}
					break;
				case 5:
					break loop;
			}


		}

		try {
			int sum1 = math.sumOddNumbers(5, 2);
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum1 + " " + sum2);
		} catch (EvenNumberException e) {
			e.printStackTrace();
		} catch (DuplicateInputException e) {
			e.printStackTrace();
			System.out.println("You passed two of the same number, try not to do that!");
		}


		/*
		 * If you want to handle multiple exceptions with the same handler logic
		 * use a pipe ( '|' ) to separate each Exception class in the catch
		 * statement
		 */
		try {
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum2);
		} catch (EvenNumberException | DuplicateInputException e) {
			e.printStackTrace();
		}


		/*
		 * When handling multiple exceptions, Exceptions higher
		 * in your exception hierarchy (parent classes) should
		 * appear later in your catch/handler blocks.
		 *
		 * This is because Java evaluates your catch statements
		 * from top-to-bottom
		 */
		try {
			int sum2 = math.sumDifferentOddNumbers(2,5);
			System.out.println(sum2);
		}
		catch (EvenNumberException | DuplicateInputException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
