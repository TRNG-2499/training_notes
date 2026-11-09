package com.revature.oop.inheritance.petstore.animals;

public class Dog extends Animal {

	// child classes can add additional fields
	// to their construction - this is in addition to
	// any they inherit from their parent
	String breed;
	
	public Dog(String name, int age, String breed) {
		super(name, age, "Dog");
		this.breed = breed;
	}
	
	public void bark() {
		System.out.println("Woof Woof");
		System.out.println("My name is: " + this.name);
	}
}
