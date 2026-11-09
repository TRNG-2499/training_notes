package com.revature.oop.polymorphism.polymorphicreferences;

import com.revature.oop.polymorphism.methods.*;

public class Simulator {

	/*
	 * Covariance is most commonly used when passing arguments
	 * to methods (i.e. defining the parameters of a method)
	 */
	public static void readBook(Book book) {
		book.read();
	}

	public static void main(String[] args) {
		/*
		 * Covariance (a.k.a. covariant typing) allows you to reference a parent class
		 * as if it were a child and vice versa.
		 */

		// Create a parent class reference, using a child class
		Book pamphlet = new Pamphlet("Why you should be a programmer", 1, "Because its so fun!");
		Book dictionary = new Dictionary("Oxford Dictionary 3rd Edition", 1200);
		Book book = new Comic("Marvel Comics", 100, "Stan Lee", "A good artist");
		Book b = new Book("My Book", 5);

		readBook(pamphlet);
		readBook(dictionary);
		readBook(book);
		
		Book[] bookshelf = new Book[2];
		bookshelf[0] = pamphlet;
		bookshelf[1] = dictionary;

		// You retain more flexibility with your objects, if you reference the parent class
//		Dictionary[] myDictionaryBookshelf = new Dictionary[3];
//		myDictionaryBookshelf[0] = dictionary;

		/*
		 * Here, we are trying to look at the child class functionality despite the fact
		 * that we have a reference variable that is pointing to a Parent class. Here,
		 * we use the cast operation to treat the variable as if it were the child
		 * class.
		 */
		( (Dictionary) dictionary).addDefinition("Apple:A red fruit");

		// The syntax below is effectively doing the same thing as above.
		Dictionary dictReference = (Dictionary) dictionary;
		dictReference.addDefinition("Basketball: An orange ball that bounces well");

		// Java allows me to treat a reference variable in a heirarcy, anywhere up that heirarcy, or down it's own inheritance tree
//		((Comic) dictionary).author = "Joseph";

		/*
		 * Note, the following also works. This is because Java only checks if the type
		 * we are casting to is a subclass of the variable, not the actual type of the
		 * variable.
		 * 
		 * Since Dictionary is a subclass of the Book class, we are able to cast. This
		 * will, however, result in an error when we actually execute our method,
		 * because the pamphlet object is not a Dictionary.
		 * 
		 * This would be a circumstance where the 'instanceof' operator would be
		 * useful:
		 */
		if (pamphlet instanceof Dictionary) {
			((Dictionary) pamphlet).addDefinition("This won't actually work");
		}

		// The below doesn't throw a compile-time error!
		((Dictionary)pamphlet).addDefinition("This won't actually work");
	}
}
