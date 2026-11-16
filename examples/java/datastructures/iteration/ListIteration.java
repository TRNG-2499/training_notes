package com.revature.datastructures.iteration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIteration {
	public static void main(String[] args) {
		System.out.println("::: LISTS :::");
		List<String> alist = new ArrayList<String>();
		alist.add("Assembly");
		alist.add("Binary");
		alist.add("Compile");
		alist.add("Compile");
		alist.add("Compile");
		alist.add("DataStructure");
		alist.add("Functions");
		
		/*
		 * Using lists, we can access the size and indexes
		 * to traverse the collection, similar to Arrays we
		 * have examined in the past
		 */
		for (int i = 0; i < alist.size(); i++) {
			System.out.println(alist.get(i));
		}

		/*
		 * Traversal through the ArrayList using the implementation
		 * of the Iterable Interface (this returns an iterator object
		 * that performs the traversal through the ArrayList)
		 */
		System.out.println("List - Enhanced For-Loop");
		for (String name : alist) {
			System.out.println(name);
		}
		
		// Iterator - For Loop - this is what the enhacned for loop does!!
		System.out.println("List - For Loop");
		for (Iterator<String> i = alist.iterator(); i.hasNext(); ) {
			String name = i.next();
			System.out.println(name);
		}

		System.out.println("List - While Loop");
		Iterator<String> aItr = alist.iterator();
		while (aItr.hasNext()) {
			System.out.println(aItr.next());
		}
	}
}
