package com.revature.modernjava.controlledinheritance;

/*
 * Vehicle is public (anyone can USE it), but only Car, Truck and Motorcycle
 * because it is a sealed class.
 *
 * By also making this class abstract, we cannot create a plain 'Vehicle'
 * it can only exist through one of its subtypes
 *
 * All classes which extend a sealed class MUST use one of three modifiers:
 *  final (see Car.java)
 *  sealed (see Truck.java)
 *  non-sealed (see Motorcycle.java)
 */
public abstract sealed class Vehicle permits Car, Truck, Motorcycle {
    private final String licensePlate;

    protected Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String licensePlate() {
        return licensePlate;
    }

    public abstract int wheelCount();
}
