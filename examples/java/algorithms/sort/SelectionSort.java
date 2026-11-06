package com.revature.algorithms.sort;

import com.revature.algorithms.util.AlgoUtil;

/*
 * Selection Sort is an iterative algorithm which sorts elements by 
 * searching through a collection and finding the lowest value's index
 * then moving that element to the front of the collection using a swap.
 * 
 * This algorithm has a complexity of:
 * Time: O(n^2)
 * 		This algorithm always compares each element, meaning this algorithm
 * 		will always exponentially increase in time-complexity as you have
 * 		more values in the collection.
 * 
 * Space: O(1)
 * 		Since this algorithm does not create any additional information
 * 		it has no additional space complexity. 
 */
public class SelectionSort {
	public static void main(String[] args) {
		Integer[] arr = { 53, 79, 15, 31, 64, 23, 41, 90, 83 };
		
		System.out.println("Given Array");
		AlgoUtil.printArray(arr);
		
		SelectionSort ss = new SelectionSort();
		ss.selectionSort(arr);
		
		System.out.println("\nSorted array");
		AlgoUtil.printArray(arr);
	}
	
	public void selectionSort(Integer[] values) {
		for (int i = 0; i < values.length - 1; i++) {
			int lowIndex = i;
			for (int j = i + 1; j < values.length; j++) {
				if (values[j] < values[lowIndex]) {
					lowIndex = j;
				}
			}
			AlgoUtil.swap(values, i, lowIndex);
		}
	}
}
