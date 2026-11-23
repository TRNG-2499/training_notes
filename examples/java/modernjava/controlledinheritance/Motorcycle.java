package com.revature.modernjava.controlledinheritance;

/*
 * By declaring the motorcycle as 'non-sealed', this branch
 * of the inheritance hierarchy has been deliberately REOPENED
 * so that any class can extend Motorcycle.
 *
 * You should be careful and make sure you have valid reasons
 * for non-sealing your hierarchy!
 */
public non-sealed class Motorcycle extends Vehicle {

    public Motorcycle(String plate) {
        super(plate);
    }
    @Override
    public int wheelCount() {
        return 2;
    }
}

class Scooter extends Motorcycle {
    public Scooter(String plate) {
        super(plate);
    }
}
