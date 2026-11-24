package com.revature.lambdas.interfaces;

/*
 * Functional Interfaces are interfaces in Java with a single
 * abstract method (not a single method)
 *
 * The '@FunctionalInterface' annotation is used to provide
 * compile-time safety for expected functional interfaces
 *
 * i.e. an interface annotated with '@FunctionalInterface' that
 * does not have a single abstract method will not compile
 */
@FunctionalInterface
public interface HelloWorldInterface {
    public void sayHello();

    public default void greet(String name) {
        System.out.println("Hello ".concat(name));
    }
}
