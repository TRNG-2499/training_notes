package com.revature.modernjava.recordpatterns;

/*
 * Records for use in Nesting examples:
 */
record Point(int x, int y) { }
record Line(Point start, Point end) { }

record Address(String city, String state) { }
record Customer(String name, Address address) { }
record Order(String id, Customer customer, double total) { }

public class Nesting {

    // Without Nesting Records Pattern: you must match the outer type, then chain accessors...
    static double lengthOld(Object obj) {
        if (obj instanceof Line line) {
            int dx = line.end().x() - line.start().x(); // distance of x
            int dy = line.end().y() - line.start().y(); // distance of y

            return Math.hypot(dx, dy); // returns vector (length) for distance between start to end
        }

        return 0; // default to return is obj is not a Line
    }

    // With Nesting Pattern: We can condense the syntax + bind variables in the instanceof check
    static double length(Object obj) {
        if (obj instanceof Line(Point(int x1, int y1), Point(int x2, int y2))) {
            return Math.hypot(x2 - x1, y2 - y1);
        }
        return 0;
    }

    /*
     * With a nested pattern, every level must match.
     *
     * Note: In the following example, instead of using 'Order'
     * as the type, Object allows for potential flexibility (a message
     * containing a List<Object> ...
     *
     *
     * BONUS NOTES: Java has support for the keyword 'var' which allows Java
     * to match a Datatype during runtime! Note: if using Var, Java will treat
     * the datatype as a Generic Object during compile time, so you cannot
     * leverage any specific functionality with your own custom classes or
     * any built-in Java classes (aside from Object)
     *
     * Additionally, using 'var' just makes this flexible for use with Records.
     * We could (in theory) change the types on the Record, and we don't have to
     * duplicate the effort here
     */
    static String shippingData(Object obj) {
        if (obj instanceof Order(var id,
                                 Customer(var name,
                                          Address(var city, var state)),
                                 var total)) {
            return name + " - " + city + ", " + state + " (order " + id + ")";
        }
        return "Cannot ship: missing customer or address";
    }
}

