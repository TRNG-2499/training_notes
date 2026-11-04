
package com.revature.javaBasics.staticexample;

public class StaticExample {

	private int myInstanceInt;
	public static int myStaticInt;

	public void increment() {
		this.myInstanceInt++;
		myStaticInt++;
	}

//	 public static void incrementStatic(){
//	 	myInstanceInt++;	// Instance variables exist at an object/instance level
//	 	myStaticInt++;
//	 }
	
	public int getMyInstanceInt() {
		return this.myInstanceInt;
	}
	
	public static int getMyStaticInt() {
//		this.myInstanceInt; // static methods cannot access instance fields arbitrarily
						// instance fields require an object to exist in the scope of the static method
		return myStaticInt;
	}


	class Owner {
		Dog pet;
		Dog getPet() { return pet; };
	}

	class Dog {

	}

	public static void main(String... args) {
		// The following syntax shows how you can create an object, but reference a field
		// on the object, rather than creating a reference variable to point to the entire object
		int instanceVal = new StaticExample().getMyInstanceInt();


		int staticVal =getMyStaticInt();

		// Accesses a static variable 'myStaticInt', directly via the class name...
		StaticExample.myStaticInt = 1;
//		StaticExample.myInstanceInt = 100; // Instance variables require an object to be created first!

		StaticExample se = new StaticExample();
		se.myInstanceInt = 100;
		System.out.println(se.myInstanceInt);	// 100
		se.myStaticInt = 5;
		System.out.println(se.myStaticInt);        // 5

		StaticExample se2 = new StaticExample();
		se2.myStaticInt = 10;
		System.out.println("se:" + se.myStaticInt);		// 10
		System.out.println("se2:" + se2.myStaticInt);	// 10
	}



	/*
	 * The following showcases the Singleton Design Pattern, using 'static'
	 */
	private static StaticExample singletonRef = new StaticExample();

	public static StaticExample getInstance() {
		if (singletonRef == null)
			singletonRef = new StaticExample();

		return singletonRef;
	}

	// you cannot create this object outside of this class...
	private StaticExample() {}
}
