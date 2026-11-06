package com.revature.algorithms.sort;

import com.revature.algorithms.util.AlgoUtil;

/*
 * Insertion Sort is an iterative algorithm which sorts elements by
 * growing a "sorted" section at the front of the array one element
 * at a time. For each new element, it is walked backwards through
 * the sorted section, swapping with its neighbor, until it lands in
 * its correct position relative to the elements already sorted.
 *
 * This algorithm has a complexity of:
 * Time: O(n^2) worst/average case, O(n) best case
 * 		In the worst case (array sorted in reverse order), every new
 * 		element must be walked all the way back through the sorted
 * 		section, resulting in the same nested-loop behavior as Bubble
 * 		and Selection sort, giving O(n^2). However, unlike those two
 * 		algorithms, if the array is already sorted (or nearly sorted),
 * 		the inner loop exits immediately for each element, giving a
 * 		best-case time of O(n). This makes Insertion Sort well suited
 * 		for small or mostly-sorted collections, which is why it is
 * 		commonly used as the base case for hybrid algorithms like
 * 		Timsort.
 *
 * Space: O(1)
 * 		Since this algorithm sorts in place and does not create any
 * 		additional collections, it has no additional space complexity.
 */
public class InsertionSort {
    public static void main(String[] args) {
        Integer[] arr = { 53, 79, 15, 31, 64, 23, 41, 90, 83 };

        System.out.println("Given Array");
        AlgoUtil.printArray(arr);

        InsertionSort is = new InsertionSort();
        is.insertionSort(arr);

        System.out.println("\nSorted array");
        AlgoUtil.printArray(arr);
    }

    public void insertionSort(Integer[] values) {
        for (int i = 1; i < values.length; i++) {
            int j = i;
            // Walk the newly considered element backwards through the
            // sorted section until it is no longer smaller than its
            // left neighbor.
            while (j > 0 && values[j - 1] > values[j]) {
                AlgoUtil.swap(values, j - 1, j);
                j--;
            }
            System.out.println("Cycle: " + i);
            AlgoUtil.printArray(values);
        }
    }
}
