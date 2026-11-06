package com.revature.algorithms.util;

public class AlgoUtil {
	/* Swap position of two indexes of an array */
	public static void swap (Object[] arr, int l_index, int r_index) {
		Object temp = arr[l_index];
		arr[l_index] = arr[r_index];
		arr[r_index] = temp;
	}
	
	/* A utility method to print array of size n */
	public static void printArray(Object arr[]) {
		int n = arr.length;
		for (int i = 0; i < n; ++i)
			System.out.print(arr[i] + " ");
		System.out.println();
	}
}
