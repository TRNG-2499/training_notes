/**
 * @author Richard Orr
 */

package com.revature.javaBasics.wrapperclasses;

import java.util.Arrays;
import java.util.Scanner;

public class WrapperClasses {
	/*
	 * primitives: boolean - ? (JVM dependent) - Boolean byte - 1 bytes - Byte char
	 * - 2 bytes - Character short - 2 bytes - Short int - 4 bytes - Integer float -
	 * 4 bytes - Float long - 8 bytes - Long double - 8 bytes - Double
	 */
	public static void main(String[] args) {

		int num = 9;
		Integer number = num; // Autoboxing - java can convert between the primitive and wrapper equivalent automatically

		Integer otherNumber = Integer.valueOf("10");
		int other = otherNumber; // (Auto) Unboxing - java can also convert from the wrapper back to the primitive, automatically

		/*
		 * Showing how to find the maximum value for each primitive through the wrapper
		 * class
		 */
//		System.out.println(Byte.BYTES);
//		System.out.println("Max Long = " + Long.MAX_VALUE);
//		System.out.println("Max Double = " + Double.MAX_VALUE);
		Byte by = null;
		 comparingPrimitivesAndWrappers();
//		 parsing();
//		 overloading();
//		System.out.println("6 is equal to new Integer(6)? " + doesItWork() );

	}

	/*
	 * Understanding Overloading precedence (preference) is useful for
	 * debugging your code; however, typically it is best to make your
	 * method signatures more distinct! When overloading a method, you
	 * should have a specific reason why you need the overloaded version!
	 *
	 * Overloading preference
	 * 1. Exact match
	 * 2. Implicit Casting (type conversion)
	 * 3. Boxing (auto-boxing or unboxing)
	 * 4. varargs
	 */
	private static void overloading() {
		int intPrimitive = 5;
		Integer intObject = 5;
		Integer intObjectAgain = Integer.valueOf(5); //

		short shortPrimitive = 5;
		Short shortObject = 5;

		long longPrimitive = 5;
		Long longObject = 5l;

		float floatPrimitive = 5;
		Float floatObject = 5.0f;

		double doublePrimitive = 5;
		Double doubleObject = 5.0;

		method(intPrimitive);
		method(shortPrimitive);
		method(intPrimitive, longPrimitive);
		method(intPrimitive, longObject);
		method((int) shortPrimitive, longPrimitive);
		method(Integer.valueOf(5), 5, 5, 5, 5, 5);
	}

	public static void method(int x) {
		System.out.println("primitive int passed: " + x);
	}

	public static void method(Short x) {
		System.out.println("object Short passed: " + x);
	}

	public static void method(Integer x, long y) {
		System.out.println("object Integer, primitive long passed: " + x + ", " + y);
	}

	public static void method(int y, Integer... x) {
		System.out.println("varargs int passed: " + Arrays.toString(x));
	}





	private static void parsing() {
		Scanner scan = new Scanner(System.in);
		String s = scan.nextLine();

		Double d = Double.parseDouble(s); 	// Wrapper classes have their own 'parse' method
											// i.e. Integer has 'parseInteger'
		System.out.println(d);
	}




	private static void comparingPrimitivesAndWrappers() {
		int intPrimitive = 5;
		Integer intObject = 5; // Integer.valueOf(5);

		short shortPrimitive = 5;
		Short shortObject = 5;

		long longPrimitive = 5l;
		Long longObject = 5l;

		// 5 is an integer. 5.0 is a double. 5f or 5.0f is a float
		float floatPrimitive = 5.0f;
		Float floatObject = 5.0f;

		double doublePrimitive = 5;
		Double doubleObject = 5.0;

		// Auto-unboxing - Then jvm will automatically "unwrap" the object to a
		// primitive type
		System.out.println("intPrimitive == intObject: " + (intPrimitive == intObject));

		// the == operator compares memory locations of objects
		System.out.println("int Object == new Integer(5): " + (intObject == Integer.valueOf(5)));

		// compare values of objects using .equals
		System.out.println("intObject.equals(new Integer(5): " + (intObject.equals(Integer.valueOf(5))));

		// always returns false if the object is not of the same type
		System.out.println("intObject.equals(longObject): " + (intObject.equals(longObject)));

		// We can auto-box 5 to Integer
		System.out.println("intObject.equals(5): " + intObject.equals(5));
		System.out.println("intObject.equals(longObject.intValue()): " + intObject.equals(longObject.intValue()));

		// type-casting (primitives) return true with '==' when their value is the same
		System.out.println("intPrimitive == shortPrimitive: " + (intPrimitive == shortPrimitive));
		System.out.println("shortPrimitive == intPrimitive: " + (shortPrimitive == intPrimitive));

	}

	public static boolean doesItWork() {
		Integer x = 6;
		Integer xo = Integer.valueOf(6);
		return x == xo;
	}
}
