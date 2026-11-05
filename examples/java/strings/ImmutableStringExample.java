package com.revature.strings;

public class ImmutableStringExample {

	public static void main(String[] args) {
		proveImmutability();
		compareStrAndStrBuilder();
		useConcat();
	}

	public static void proveImmutability() {
		// can you change the value of this string?
		String s0 = "a";
		s0.concat("bcd"); // s0 is not being affect
		System.out.println(s0); // why does this not print abcd? because strings are immutable
		// so lets try something else
		String s0a = s0.concat("bcd");// must assign to new variable in order to get the string "abcd"
		System.out.println(s0a); // as you can see, the value of s0 never changes
	}

	public static void compareStrAndStrBuilder() {
		// create two strings with same values
		String s1 = "Hello world";
		String s2 = "Hello world";
		System.out.println(s1 == s2);

		// create two StringBuilders with same values
		StringBuilder s3 = new StringBuilder("First string");
		StringBuilder s4 = new StringBuilder("First string");
		System.out.println(s3 == s4);
	}

	public static void useConcat() {
		// use the concat operator and compare
		String s5 = "Hello again";
		String s6 = s5.concat("");
		System.out.println(s5 == s6);
		String s7 = s5.concat(" and again");
		System.out.println(s7 == s6);
	}

}
