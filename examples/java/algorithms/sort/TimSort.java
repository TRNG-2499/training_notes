package com.revature.algorithms.sort;

import com.revature.algorithms.util.AlgoUtil;

/*
 * Timsort is a hybrid, iterative-and-recursive sorting algorithm that
 * combines Insertion Sort and Merge Sort. It was designed to take
 * advantage of "runs" of already-ordered elements that occur in
 * real-world data.
 *
 * The algorithm works in two phases:
 * 	1. The array is divided into small chunks called "runs" (here a
 * 	   fixed RUN size is used for simplicity; real implementations
 * 	   compute a variable minrun and detect naturally occurring runs).
 * 	   Each run is sorted individually using Insertion Sort, since
 * 	   Insertion Sort is very fast on small collections.
 * 	2. The sorted runs are then combined back together using the same
 * 	   merge() logic as Merge Sort, doubling the merged run size on
 * 	   each pass until the entire array is one sorted run.
 *
 * This algorithm has a complexity of:
 * Time: O(n log(n)) worst case, O(n) best case
 * 		The merge phase behaves exactly like Merge Sort, contributing
 * 		a logarithmic number of passes (log n) over the array, each
 * 		doing O(n) work to merge, giving O(n log n) overall. Unlike a
 * 		plain Merge Sort, however, Timsort's initial Insertion Sort
 * 		pass lets it detect and skip merging work on runs that are
 * 		already in order, so a fully or mostly sorted input can be
 * 		sorted in close to O(n) time.
 *
 * Space: O(n)
 * 		Like Merge Sort, the merge phase requires temporary arrays
 * 		to hold each half being merged, giving Timsort the same O(n)
 * 		space complexity. The Insertion Sort phase itself only adds
 * 		O(1) space on top of that.
 */
public class TimSort {
    /* Size of each run that is sorted with Insertion Sort before merging. */
    private static final int RUN = 8;

    /* Simulator Method */
    public static void main(String[] args) {
        // larger array  to showcase TimSort runs in action
        Integer[] arr = { 655, 115, 26, 760, 282, 251, 229, 143, 755, 105, 693, 759, 14, 559, 90, 605, 433, 33, 31, 53, 79, 15, 31, 64, 23, 41, 90, 83, 96, 224, 239, 518, 617, 28, 575, 204, 734, 666, 719, 986, 430, 226, 460, 604, 285, 829, 7, 778 };

        System.out.println("Given Array");
        AlgoUtil.printArray(arr);

        TimSort ts = new TimSort();
        ts.timSort(arr);

        System.out.println("\nSorted array");
        AlgoUtil.printArray(arr);
    }

    /* Phase 1: sorts an individual run in place using Insertion Sort. */
    void insertionSort(Integer[] values, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int j = i;
            while (j > left && values[j - 1] > values[j]) {
                AlgoUtil.swap(values, j - 1, j);
                j--;
            }
        }
    }

    /* Merges two sorted sub-arrays of arr[].
     * First sub-array is arr[l..m] > l = left index, m = midpoint
     * Second sub-array is arr[m+1..r] > m = midpoint, r = right index
     */
    void merge(Integer[] arr, int l, int m, int r) {
        /* Find sizes of two sub-arrays to be merged */
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays back into arr[l..r] */
        int i = 0, j = 0;
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

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    /* Driving method: sorts runs with Insertion Sort, then repeatedly
     * merges neighboring runs, doubling the merged size each pass.
     */
    public void timSort(Integer[] values) {
        int n = values.length;

        // Phase 1: sort individual runs of size RUN using Insertion Sort
        for (int start = 0; start < n; start += RUN) {
            int end = Math.min(start + RUN - 1, n - 1);
            insertionSort(values, start, end);
        }
        System.out.println("After initial run sort:");
        AlgoUtil.printArray(values);

        // Phase 2: merge runs, doubling the merged size on each pass
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = Math.min(left + size - 1, n - 1);
                int right = Math.min(left + 2 * size - 1, n - 1);

                if (mid < right) {
                    merge(values, left, mid, right);
                }
            }
            System.out.println("After merge pass (size=" + size + "):");
            AlgoUtil.printArray(values);
        }
    }
}
