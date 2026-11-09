package com.revature.oop.abstraction.interfaces;

/*
 * Interfaces can extend multiple interfaces!
 *
 * Interfaces which need to extend other interfaces
 * will use the extends keyword. Note that you can
 * extend as many interfaces as you want in an interface
 *
 * If an interface is inheriting multiple methods from
 * two or more interfaces that share the same signature (name/param list)
 *
 * Then one implementation will be used to satisfy all inherited
 * interface methods!
 *
 * You should try to avoid this as much as possible in your own code!!!
 */
public interface MultiInherit extends Aquatic, Flyable {
}
