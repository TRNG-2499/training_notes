package com.revature.algorithms.search;

/* 
 * Binary Search is a search algorithm which finds an element by comparing a target element value
 * to a shifting "midpoint". This "midpoint" is based on the remaining search area, which is 
 * determined depending on the target number's value, as compared to the midpoint. A Binary search
 * only works on sorted collections. 
 * 
 * This algorithm has a complexity of:
 * Time: O(log n)
 * 		This algorithm reduces the search area by half during each cycle
 * 		so this algorithm is favored as you have increasingly larger collections.
 * 
 * Space: O(1)
 * 		Since this algorithm does not create any additional information
 * 		it has no additional space complexity. 
 */
public class BinarySearch {
	public static void main(String[] args) {
		Integer[] arr = { 15, 23, 31, 41, 53, 64, 79, 83, 90 };

		BinarySearch bs = new BinarySearch();
		System.out.println(bs.binarySearch(35, arr)); // -1
		System.out.println(bs.binarySearch(64, arr)); // 5
	}

	public int binarySearch(int target, Integer[] collection) {
		int left = 0;
		int right = collection.length - 1;

		while (left < right) {
			int mid = (left + right) / 2;

			if (target < collection[mid])
				right = mid - 1;
			else if (target < collection[mid])
				left = mid + 1;
			else
				return mid;

		}
		return -1;
	}
}
