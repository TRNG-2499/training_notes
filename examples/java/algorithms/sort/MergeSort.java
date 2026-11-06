package com.revature.algorithms.sort;

import com.revature.algorithms.util.AlgoUtil;

/*
 * The Merge sort algorithm is a recursive sorting algorithm which 
 * sorts elements by splitting the collection into smaller and smaller
 * halves and re-combining those back into a sorted collection.
 * 
 * This algorithm has a complexity of:
 * Time : O(n log(n))
 * 		This time complexity of a merge sort is actually consistent regarding
 * 		the best and worst-cases. The reason is that the merging of subArrays
 * 		is split between two actions, a logarithmic half-step (log n) and an 
 * 		n merge (n merge for the number of elements in that step to merge back
 * 		together to the new collection).
 * 
 * Space : O(n)
 *		The space complexity of a Merge sort is O(n) because the algorithm 
 *		uses the same number of resources for each step of the splits and
 *		merge. For instance, sorting 8 elements. 
 *						  ______8______			----	8
 *						 |  	       |
 *					   __4__         __4__		----	8
 *					  |     |       |     |
 *				     _2_   _2_     _2_   _2_    ----	8
 *				    |   | |   |   |   | |   |
 *				    1   1 1   1   1   1 1   1	----	8
 *
 *		Although this may seem like we are doubling the size on each step,
 *		this algorithms does not maintain these instances after each recursive
 *		call, so the space for each step is not added, but rather replaced with
 *		each step.
 */
public class MergeSort {
	/* Simulator Method */
	public static void main(String args[]) {
		Integer arr[] = { 53, 79, 15, 31, 64, 23, 41, 90, 83 };

		System.out.println("Given Array");
		AlgoUtil.printArray(arr);

		MergeSort ms = new MergeSort();
		ms.sort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		AlgoUtil.printArray(arr);
	}
	
	/* Merges two sub-arrays of arr[].
	 * First sub-array is arr[l..m] > l = left index, m = midpoint
	 * Second sub-array is arr[m+1..r] > m = midpoint, r = right index
	 */
	void merge(Integer arr[], int l, int m, int r) {
		/* Find sizes of two sub-arrays to be merged */
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) {
			arr[k] = L[i];
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2) {
			arr[k] = R[j];
			j++;
			k++;
		}
	}

	/* sorting method which splits sorted
	 * halves and calls merge() at the end
	 * to combine split, and sorted collections
	 */
	void sort(Integer arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			sort(arr, l, m);
			sort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}
}
