package com.revature.strings;

public class StringDriver {

	public static void main(String[] args) {
		
		/*
		 * String Pool
		 * 
		 * The String Pool is an area within the Heap (where objects are stored) that is reserved for String
		 * objects. Strings are one of the most commonly used classes in Java, and since they are Objects they
		 * tend to take up a lot of memory. In order to help manage this memory, Java provides the String Pool.
		 * 
		 * The String Pool holds String objects that are instantiated using String literals, and if the two
		 * different Strings are instantiated using an identical String literal, then they point to the same
		 * String object within the String Pool. However, if the 'new' keyword is used to instantiate the
		 * String, then it will force Java to create the String object within the Heap space, outside of the 
		 * String Pool.
		 */
		
		// declared using the String literal "hi"; first time instantiated, so it is stored in the String Pool
		String str1 = "hi";
		
		// declared using the String literal "hi"; an identical object already exists in the String Pool, so str2 references it
		String str2 = "hi";
		
		// declared using the 'new' keyword and the String constructor; the String is stored in the Heap
		String str3 = new String("hi");
		

		
		System.out.println(str1 == str2); // true 
		System.out.println(str2 == str3); // false
		System.out.println(str1 == str3); // false
		
		System.out.println("str2 changed to be 'hello'");
		str2 = "hello";

		System.out.println(str1 == str2); // false

		// declared using the String literal "hi"; an identical object already exists in the String Pool, so str4 references it
		String str4 = "hi";
		System.out.println(str1 == str4); // true

		System.out.println("st4 reassigned to the object referenced by str3");
		str4 = str3;

		System.out.println(str3 == str4); // true

		System.out.println(str1.equals(str3)); // true

		System.out.println(" == with intern()");
		System.out.println(str1 == str3); // false
		System.out.println(str1 == str3.intern()); // true


		// replaceAll
		System.out.println(" || replaceAll ||");
		String test = "Here is my string. Isn't it cool?";
		test.replaceAll(" ", "_"); // Without being in a println() or the return value being assigned, this doesn't do much
		System.out.println(test); // test is unchanged, because Strings are immutable!
		System.out.println(test.replaceAll(" ", "_")); // this works though, the String returned by the method is output to the console

		// charAt
		System.out.println(" || charAt ||");
		String original = "original string";
		System.out.println(original.charAt(1));

		// lowercase & uppercase
		System.out.println(" || toLowerCase ||");
		String str = "CrAzY cAsE!";
		str = str.toLowerCase(); // we need to capture the returned value (Strings are immutable)
		System.out.println(str);
		System.out.println(" || toUpperCase ||");
		System.out.println(str.toUpperCase());

		// ends with
		System.out.println(" || endsWith ||");
		String myStr = "running";
		boolean endsWithING = myStr.endsWith("ING".toLowerCase()); // casing is important!
		System.out.println(endsWithING); // true
		
	}
}
