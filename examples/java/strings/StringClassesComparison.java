package com.revature.strings;

public class StringClassesComparison {
	static int n = 200000; // change this to increase or decrease length of loop

	public static void main(String[] args) {
		createManyStrings();
		useStringBuilder();
		useStringBuffer();
		// StringBuffer is like StringBuilder, but it is synchronized and thus threadsafe
		System.out.println("As you can see, StringBuilder & StringBuffer are much faster than String!");
	}

	public static void createManyStrings() {
		String s = "";
		long timeInit = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			s = s.concat("a");
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time when using String: " +(timeEnd-timeInit) + " ms");
	}
	
	public static void useStringBuilder() {
		StringBuilder sb = new StringBuilder();
		long timeInit = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			sb.append("a");
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time when using StringBuilder: " +(timeEnd-timeInit) + " ms");
	}
	
	public static void useStringBuffer() {
		StringBuffer sb = new StringBuffer();
		long timeInit = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			sb.append("a");
		}
		long timeEnd = System.currentTimeMillis();
		System.out.println("Time when using StringBuffer: " +(timeEnd-timeInit) + " ms");
	}

}
