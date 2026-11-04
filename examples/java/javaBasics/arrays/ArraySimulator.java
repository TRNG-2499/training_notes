/**
 * @author Wezley Singleton
 */

package com.revature.javaBasics.arrays;

// The 'Arrays' class is a Utility class in the java.util package
// which provides convenience methods that work with Array objects
// One such (seen below) is the 'toString' method, that will print
// the elements of the given array
import java.util.Arrays;

public class ArraySimulator {
	public static void main(String[] args) {
		
		/*
		 * Arrays
		 * 
		 * An array is a group of like-typed variables that are referred to by a common name. Some things 
		 * to note about arrays in Java include:
		 * 
		 * 		- Arrays are of a fixed size. This means that once they are instantiated (yes, arrays are 
		 * 		  objects) their size can not be changed. 
		 * 
		 * 		- The variables in an array are ordered and each have an index beginning from 0. 
		 * 
		 * 		- The size of an array must be specified by an int value and not long or short.
		 * 
		 * 		- Can be multi-dimensional (an array of arrays)
		 * 
		 * 		- The length of an array can be found by invoking the 'length' member on any array
		 */
		
		// Arrays can be declared in one of two ways:
		int myIntArray[]; // this a valid array declaration
		int[] myOtherIntArray; // this is also a valid array declaration
		
		// Likewise, there are a few ways to instantiate arrays:
		myIntArray = new int[5]; // valid
		myOtherIntArray = new int[] {6, 4, 2, 5, 1, 9}; // values
		
		// array literals by themselves need to be included on the same line as the array's declaration
		char[] myCharArray = {'a', 'b', 'c'};

		/*
		 * All array values are 'indexed' meaning that we can access them
		 * based on their position, in order.
		 *
		 * Array indexing starts at 0
		 *
		 * ex:
		 * myOtherIntArray = new int[] {6, 4, 2, 5, 1, 9};
		 * index 0 of myOtherIntArray is the value 6
		 * index 1 of myOtherIntArray is the value 4
		 * index 2 of myOtherIntArray is the value 2
		 * index 3 of myOtherIntArray is the value 5
		 * ...
		 *
		 */
		System.out.println( myOtherIntArray[0] ); // 6
		System.out.println( myOtherIntArray[1] ); // 4
		System.out.println( myOtherIntArray[2] ); // 2

		// throws ArrayIndexOutOfBoundsException (array indexes are zero-based and of a fixed size!)
//		System.out.println( myOtherIntArray[10] ); // error
						 // index 6 and above are out of bounds for this array

		myOtherIntArray[0] = 17;
		System.out.println( myOtherIntArray[0] ); // 17
		
		// The length field of the all arrays is actually a final member (constant, cannot be changed)
		System.out.println("Length of char array: " + myCharArray.length); // how many elements exist in this array? - 5
		
		// Use a standard for loop to iterate through the loop
		for (int i = 0; i < myCharArray.length; i++) {
			System.out.println(myCharArray[i]);
		}

		System.out.println("---------");

		// A for-each loop can also be used to iterate through an array
		for(char c : myCharArray) {	// even though myIntArray is an array of ints, they will be autoboxed into the Integer wrapper class
			System.out.println(c);
		}

		System.out.println("----------");

		// We can make arrays with objects, like Strings, as well
		String[] names = new String[3];
		names[0] = "Adam";
		names[1] = "Brittany";
		names[2] = "Charles";

		for(String name : names) {
			System.out.println(name);
		}

		System.out.println("---------");

		// multi-dimensional arrays!
		// The first 'array' level determines the count of array objects
		// the second 'array' level determines the count of elements per array object
		int[][] the2dArray = new int[4][5];
		/*
		 *	{
		 * 		{1, 2, 3, 4, 5},
		 * 		{1, 2, 3, 4, 5},
		 * 		{1, 2, 3, 4, 5},
		 * 		{1, 2, 3, 4, 5}
		 * 	}
		 */
		int[][] my2dArray = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

		int value = my2dArray[1][2]; // grab the array within the 2d array at position 1, then grab the value at position 2 within it
		System.out.println(value);

		System.out.println(my2dArray[1]); // prints out an object memory reference, arrays are objects!
		System.out.println(Arrays.toString(my2dArray[1])); // [4, 5, 6]

		// Use nested loop structures to iterate through the layers of multi-dimensional arrays
		for(int i = 0; i < my2dArray.length; i++) {
			for(int j = 0; j < my2dArray[i].length; j++) {
				System.out.println("Value at position " + i + ", " + j + ": "+ my2dArray[i][j]);
			}
		}

		// Likewise, for-each loops can be used to do the same (notice the type of each element in the outer loop)
		for(int[] intArray : my2dArray) {
			for(int num : intArray) {
				System.out.println(num);
			}
		}
//
//		System.out.println("-----------");
//
//		// Arrays can be passed as arguments
//		workWithArray(myOtherIntArray);
		
	} 
	
	public static void workWithArray(int[] arr) {
		for(Integer number : arr) {
			System.out.println(number);
		}
	}

}
