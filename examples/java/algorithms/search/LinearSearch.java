package com.revature.algorithms.search;

/*
 * Linear Search is an search algorithm which locates the index of a target element
 * within a collection be comparing the target's value to each element of the collection
 * in sequential order.
 * 
 * This algorithm has a complexity of:
 * Time: O(n)
 * 		This algorithm always compares the target to each element, meaning this algorithm
 * 		can, at worst, complete a process for each element of the collection. At best it
 * 		can find the correct index in the first cycle.
 * 
 * Space: O(1)
 * 		Since this algorithm does not create any additional information
 * 		it has no additional space complexity. 
 */
public class LinearSearch {
	public static void main(String[] args) {
		Integer[] arr = { 15, 23, 31, 41, 53, 64, 79, 83, 90 };
		
		LinearSearch ls = new LinearSearch();
		System.out.println(ls.linearSearch(35, arr)); // -1
		System.out.println(ls.linearSearch(64, arr)); // 5
	}
	
	public int linearSearch(int target, Integer[] collection) {
		int index = -1;
		for (int i = 0; i < collection.length; i++) {
			if (collection[i] == target) {
				index = i;
			}
		}
		
		return index;
	} 
}
