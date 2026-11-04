/**
 * @author William Gentry
 */

package com.revature.javaBasics.constructors;

public class C extends B {

	// Variable Shadowing...again! - don't do this!
//	private int myNumber;
	
	public C() {
		super("hello there", 9999);
		System.out.println("Printing from inside C's no-arg constructor");
	}
	
	public C(int myNumber) {
		super(myNumber);
		System.out.println("Inside the 1-arg constructor of C");
		// this.myNumber = myNumber // commented out because we want to avoid variable shadowing!
	}
	
	public static void main(String[] args) {
		A a = new A(10);
		System.out.println();

		System.out.println("When constructor of B is invoked, it calls constructor of A");
		A b = new B(10);

		System.out.println();
		System.out.println("When constructor of C is invoked, it calls constructor of B, which calls constructor of A:");
		A c = new C(10);
		System.out.println();
	}
}
