package com.revature.modernjava.controlledinheritance;

/*
 * By declaring Trucks are 'sealed' we are continuing to allow
 * extension of this hierarchy, but only to a limited list of
 * permitted subclasses
 */
public sealed class Truck extends Vehicle permits DumpTruck, TowTruck, GarbageTruck {
    public Truck(String plate) {
        super(plate);
    }
    @Override
    public int wheelCount() { return 4; }
}

final class DumpTruck extends Truck {
    public DumpTruck(String plate) { super(plate); }
}

final class TowTruck extends Truck {
    public TowTruck(String plate) { super(plate); }
}

final class GarbageTruck extends Truck {
    public GarbageTruck(String plate) {
        super(plate);
    }
}