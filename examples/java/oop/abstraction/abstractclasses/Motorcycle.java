package com.revature.oop.abstraction.abstractclasses;

public class Motorcycle extends Vehicle {

	/*
	 * Recall that the @Override annotation informs java that the method it is
	 * annotating is inherited from a parent class and its implementation details
	 * are being overridden
	 */
	@Override
	public void propel() {
		System.out.println("Use the accelerator on the left handle");
	}

	@Override
	public void steer() {
		System.out.println("Somewhat similar to a bicycle");
	}
	
}
