package com.revature.javaBasics.staticexample;


public class Shadowing {

    String name;

//    static int name; // cannot re-use variable name across class / instance scope

    public Shadowing(String name) {
        // 'name' refers to the closest scoped variable that was created
        // in this case, the param 'name'
        // This is variable Shadowing, and the reason why 'this' exists!
        this.name = name;

        if (true) {
//            int name = 0; // error - cannot re-use name of a variable in blocks within a method's scope
        }
    }
}
