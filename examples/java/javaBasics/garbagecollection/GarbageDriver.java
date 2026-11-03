/**
 * @author Wezley Singleton
 */

package com.revature.javaBasics.garbagecollection;

public class GarbageDriver {

	public static void main(String[] args) {
		
		System.out.println("Instantiating a new GarbageDriver object...");
		GarbageDriver garbage = new GarbageDriver(); // Object 1
		System.out.println("GarbageDriver object successfully created!");
		
		System.out.println("Some time passes...");
		
		try {
			Thread.sleep(5000); // stop doing things for 5000 milliseconds (5 seconds)
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
		
		System.out.println("Assign the variable 'garbage' to some other GarbageDriver object");
		garbage = new GarbageDriver(); // Object 2
		
		System.gc(); // this doesn't perform garbage collection right away...

		// this is creating an infinite for loop - for the purposes of this
		// demo, just so that out program can 'stay alive'
		for(;;) {
			System.out.println("...");
		}
		
	}

	/*
	 * The method 'finalize' is called when the Java Garbage Collector determines
	 * there are no more references pointing to an object. This method is used for
	 * final cleanup operations.
	 *
	 * Here, the 'GarbageDriver' object will call 'System.exit(0)' which causes the
	 * application to stop
	 */
	@Override
	protected void finalize() {
		System.out.println("GarbageDriver's finalize() method called!");
		System.out.println("Goodbye, cruel world!");
		System.exit(0);	// System.exit(0) is a 'full-stop' on the application
	}
	
}
