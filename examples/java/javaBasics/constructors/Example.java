package com.revature.javaBasics.constructors;

/*
 * All classes (objects created from those classes) belong to a hierarchy
 *
 * This hierarchy starts with Java's Object class (java.lang.Object)
 */
public class Example extends Object {

    String name;

    /*
     * Below, by having both the no-args and the all-args constructors
     * we have overloaded the constructors (both a no-args and one that
     * passes in the String (name) param can exist simultaneously)
     */

    // This is what a default constructor looks like
    public Example() {
        // implicitly, java constructors will call the parent class
        // constructor. Here 'super' effectively 'means Object()'
        super ();
    }

    // When you create your own constructor, you lose the default (no-args) constructor
    // that java provides (you can write your own, however)
    public Example(String name) {
        // initialize the fields of my class
        this.name = name;
    }

    public static void main(String[] args) {
        // Calling Default constructor for my Example Class
        // Notice that I didn't create this constructor!
        Example e = new Example();
    }
}
