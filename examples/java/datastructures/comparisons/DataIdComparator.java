package com.revature.datastructures.comparisons;

import java.util.Comparator;

public class DataIdComparator implements Comparator<Data> {
	@Override
	public int compare(Data o1, Data o2) {
		// Very simple implementation...
		return o1.getId() - o2.getId();
	}
}
