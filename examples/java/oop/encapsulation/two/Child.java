package com.revature.oop.encapsulation.two;

import com.revature.oop.encapsulation.one.Parent;

public class Child extends Parent {

	public void printMyFields() {
		System.out.println(this.pubString);
		System.out.println(this.proString);
//		System.out.println(this.defString); // not visible
//		System.out.println(this.priString); // not visible
	}


	public static void main(String[] args) {
		Parent p = new Parent();
		System.out.println(p.pubString);
//		System.out.println(p.proString); // error - cannot access protected field on parent

		Child c = new Child();
		System.out.println(c.pubString);
		System.out.println(c.proString); // child class has access to it's inherited protected field
	}

}
