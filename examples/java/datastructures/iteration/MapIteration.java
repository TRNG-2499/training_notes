package com.revature.datastructures.iteration;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapIteration {
	public static void main(String[] args) {
		System.out.println("::: Maps :::");
		Map<String, Integer> hMap = new HashMap<String, Integer>();
		hMap.put("Assembly", 1);
		hMap.put("Binary", 2);
		hMap.put("Compile", 3);
		hMap.put("Compile", 4);
		hMap.put("Compile", 5);
		hMap.put("DataStructure", 6);
		hMap.put("Function", 7);
		
		/*
		 * Iteration using keys - Keys are stored as a Set
		 */
		System.out.println("\tMap - KeySet Loop");
		for (String key : hMap.keySet()) {
			System.out.println("Key is: " + key);
		}
		
		System.out.println("\tMap - Iterator");
		Iterator<String> mapItr = hMap.keySet().iterator();
		while(mapItr.hasNext())
			System.out.println(mapItr.next());

		/*
		 * Iteration using values - values are another Collection (no guarantee on the type of collection)
		 */
		System.out.println("\tMap - Values Loop");
		for (Integer num : hMap.values()) {
			System.out.println("Values: " + num);
		}

		/*
		 * Iteration using Entries (K-V pairs)
		 * 'Entry' is an object which contains a key and the associated value.
		 */
		System.out.println("\tMap - EntrySet Loop");
		for (Map.Entry<String, Integer> entry : hMap.entrySet()) {
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}
	}
}
