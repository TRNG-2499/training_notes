package com.revature.modernjava.controlledinheritance;

/*
 * By declaring Car as final, we are CLOSING this branch
 * of the inheritance hierarchy.
 */
public final class Car extends Vehicle {
    public Car(String plate) {
        super(plate);
    }

    @Override
    public int wheelCount() {
        return 4;
    }
}
