package com.revature.oop.abstraction;

import com.revature.oop.abstraction.abstractclasses.AquaticJet;
import com.revature.oop.abstraction.abstractclasses.Motorcycle;
import com.revature.oop.abstraction.abstractclasses.Vehicle;
import com.revature.oop.abstraction.interfaces.Aquatic;
import com.revature.oop.abstraction.interfaces.Flyable;

public class Simulator {
	public static void main(String[] args) {
		/*
		 * You will commonly see the use of Covariance with Abstract
		 * classes...
		 *
		 * Vehicle is not guaranteed to have a definition for all of
		 * its methods (because it is abstract), as such, we cannot
		 * call the Vehicle class constructor!
		 *
		 * We can only call constructors directly for a concrete
		 * class within an abstract class's hierarchy
		 */
		Vehicle v = new Motorcycle();

//		Vehicle jet = new AquaticJet();
//		jet.steer();
//		if (jet instanceof Flyable)
//			((Flyable)jet).fly();
//
//		if (jet instanceof Aquatic)
//			((Aquatic)jet).floatOnWater();
	}
}
