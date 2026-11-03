/**
 * @author Yuvi
 */

package com.revature.javaBasics.datatypes;

public class Datatypes {

	/*
	 * In Java, variables are either primitive variables
	 * or reference variables.
	 *
	 * primitive variable types include:
	 * byte, short, int, long, float, double, boolean, char
	 *
	 * Any other type is a reference variable!
	 */
	byte by = 127; // whole number up to 8 bits.
	short s = 32767; // whole number up to 16 bits.

	/*
	 * When writing numbers in Java, underscores can be used. They
	 * are commonly used every 3 digits, similar to a comma. Note, you
	 * do not have to follow this convention, but it makes your code
	 * easier to read.
	 */
	int i = 2_147_483_647; // whole number up to 32 bits.
	//	int decimal = 3.14F;
	/*
	 * Whole number literals are treated as most, as an int. If you
	 * want to treat a number as a long, you need to apply the letter
	 * 'L' after the number
	 */
	long l = 500_000_000_000L; // whole number up to 64 bits.

	/*
	 * Doubles are the default datatype for floating point numbers
	 * (i.e. decimal numbers)
	 */
	float f = 3.14F; // Floats must be declared explicity using an 'F'
	double d = 3.14;

	boolean bo = true; // true or false

	/*
	 * Java uses double quotes for strings and single quotes for chars
	 */
	char c = 'A';

	/*
	 * When you want to perform work in a Java application, it must
	 * be done within a method. "Perform work" in this case means:
	 * "execute some statements"
	 */
//	for (char character = 'A'; character < 'Z'; character++) {
//		System.out.println(character);
//	}

	/*
	 * An Object is an instance of a class.
	 *
	 * An object is a custom representation of an idea / physical entity
	 * or other significant data. The use of an object varies depending
	 * on the class composition, and your application's needs.
	 */
	Object o; //depends on the object
	public static void main(String[] args) {
		System.out.println("START OF APPLICATION");

		// Here, we invoke the 'print()' method declared below
		print();

		int i = 5;
		int j = i++; // j will b 5 | i will be 6
		int h = ++j; // j and h will be 6

		System.out.println("Printing Characters with a loop");
		for (char character = 'A'; character <= 'Z'; character++) {
			System.out.println(character);
		}

		System.out.println("END OF APPLICATION");
	}

	/*
	 * Methods (functions) are an isolated set of statements that
	 * can be invoked as a batch. Methods have a 'signature' including
	 * a name to call the method, a 'return type' and other optional
	 * metadata such as parameters (input to the method) or metadata
	 * keywords to add or limit features. (public, private, final,
	 * static, sealed, etc...)
	 */
	public static void print() {
		System.out.println("print() method invoked");
		Datatypes data = new Datatypes();
		System.out.println("byte: " + data.by);
		System.out.println("short: " + data.s);
		System.out.println("int: " + data.i);
		System.out.println("long: " + data.l);
		System.out.println("float: " + data.f);
		System.out.println("double: " + data.d);
		System.out.println("boolean: " + data.bo);
		System.out.println("char: " + data.c);
		System.out.println("object: " + data.o);
		System.out.println("print() method ended");
	}
}
