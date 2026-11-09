package com.revature.oop.abstraction.abstractclasses;

/*
 * Note: Abstract classes can extend other abstract classes
 * Within an inheritance hierarcy that includes abstract class(es)
 * the first Concrete (non-abstract) class which inherits from
 * the Abstract ones MUST provide an implementation for any
 * abstract methods which have not been implemented!
 *
 * If you don't implement inherited abstract methods, you
 * will receive a compilation error
 */
public abstract class Car extends Vehicle {
	public void propel() {
		System.out.println("Press down on the accelerator to go faster!");
	}

//	public void steer() {
//		System.out.println("Use the wheel to turn left and right");
//	}
}
