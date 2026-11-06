package com.revature.algorithms.sort;

import com.revature.algorithms.util.AlgoUtil;

/*
 * Bubble Sort is an iterative algorithm which sorts elements by 
 * comparing adjacent values and swapping their position if they
 * are out of order. 
 * 
 * This algorithm has a complexity of:
 * Time: O(n^2)
 * 		The current implementation evaluates each element multiple times 
 * 		in the array due to the nested structure, resulting in an algorithm
 * 		that always performs searches of an O(n^2) complexity. The worst case
 * 		time occurs when swaps are performed for each evaluation, meaning
 * 		the array is sorted in reverse order. An optimal implementation of 
 * 		this algorithm would break the outer loop if the inner loop performed
 * 		no swaps:
 * 
	public void bubbleSort(Integer[] values) {
		int i, j, temp, n = values.length;
		boolean swapped;
		while (i < n) {
			swapped = false;
			for (j = 0; j < n - 1 - i; j++) {
				if (values[j] > values[j + 1]) {
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					swapped = true;
				}
			}
			if (!swapped)
				break;
			
			i++;
		}
	}
 * 
 * Space: O(1)
 * 		Since this algorithm does not create any additional information
 * 		it has no additional space complexity. 
 */
public class BubbleSort {
	public static void main(String[] args) {
		Integer[] arr = { 53, 79, 15, 31, 64, 23, 41, 90, 83 };
		
		
		// { 1, 2, 3, 4, 5, 6 } 
		
		System.out.println("Given Array");
		AlgoUtil.printArray(arr);
		
		BubbleSort bs = new BubbleSort();
		bs.bubbleSort(arr);
		
		System.out.println("\nSorted array");
		AlgoUtil.printArray(arr);
	}
	
	public void bubbleSort(Integer[] values) {
		int i = 0;
		while (i < values.length) {
			for (int j = 0; j < values.length - 1 - i; j++) {
				if (values[j] > values[j + 1]) {
					AlgoUtil.swap(values, j, j+1);
				}
			}
			i++;
			System.out.println("Cycle: " + i);
			AlgoUtil.printArray(values);
		}
	}
}
