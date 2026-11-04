/**
 * @author William Gentry
 */

package com.revature.javaBasics.constructors;

/* Don't worry too much about the keyword 'extends' here
 * this is part of OOP (specifically inheritance) and will
 * be covered in more detail later...
 */
public class B extends A {

	// Why are we declaring 'myNumber' here?
	// didn't we get that from class A? - Yes (and no...)
	// Variable Shadowing...(try to avoid this...)
//	private int myNumber;
	
	public B() {
		/*
		 * I have commented out the no-args constructor in A
		 * As such, I must call the only declared constructor
		 * which looks like this:
		 * 	A (int myNumber) ...
		 *
		 * Now, my call to super() must supply an int value to
		 * match this constructor's signature. 0 is just a dummy
		 * default value
		 */
		super(0);
		System.out.println("Printing from inside B's no-arg constructor");
	}
	
	public B(int myNumber) {
		super(myNumber);
		System.out.println("Inside the 1-arg constructor of B");
//		this.myNumber = myNumber;
	}


	public B(String input, int myNumber) {
		super(myNumber);

		System.out.println("You called my two-args constructor (String, int) and gave me the String: " + input);
	}
}
