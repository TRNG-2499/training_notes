package com.revature.oop.abstraction.abstractclasses;

import com.revature.oop.abstraction.interfaces.Aquatic;
import com.revature.oop.abstraction.interfaces.Flyable;

public class AquaticJet extends Vehicle implements Aquatic, Flyable {

	@Override
	public void fly() {
		// TODO Auto-generated method stub
	}

	@Override
	public void floatOnWater() {
		// TODO Auto-generated method stub
		
	}
	
	// When inheriting the same method signature from multiple interfaces
	// a single implementation satisfies both!
	@Override
	public void move() {
		// TODO Auto-generated method stub
	}

	// This is from 'Flyable' because it's signature differs from the 'move()' method on Aquatic
	public void move(int a) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sink() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void propel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void steer() {
		// TODO Auto-generated method stub
		
	}
	
}
