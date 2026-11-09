package com.revature.oop.polymorphism.methods;

/*
 * Superclass for the Comic, Pamphlet and Dictionary classes
 */
public class Book {
	public String name;
	public int pageCount;
	
	public Book(String name, int pageCount) {
		this.name = name;
		this.pageCount = pageCount;
	}
	
	public void read() {
		System.out.println("Generic Book class read method");
	}

	public void throwBook() {

	}
}
