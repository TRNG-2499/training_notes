package com.revature.lambdas.implementors;

import com.revature.lambdas.interfaces.HelloWorldInterface;

/*
 * Traditionally, when leveraging interfaces, you must create a class
 * which uses the implements keyword and declare the interfaces
 * it is implementing. This is useful when a class is composed
 * of many functions and structure, but not so useful if the goal
 * is to implement a single, functional interface for one-off
 * implementation logic.
 */
public class HelloWorldImplementor implements HelloWorldInterface{
    @Override
    public void sayHello () {
        System.out.println("Hello World!");
    }
}
