package com.revature.oop.encapsulation.two;

import com.revature.oop.encapsulation.one.Parent;
import com.revature.oop.encapsulation.two.Child;

public class Simulator {
	public static void main(String[] args) {
		Parent p = new Parent();
		System.out.println(p.pubString);
//		System.out.println(p.proString); // error - cannot access protected field
//		System.out.println(p.defString); // error - cannot access default field
//		System.out.println(p.priString); // error - cannot access private field
		
		Child c = new Child();
		System.out.println(c.pubString);
//		System.out.println(c.proString); // error - cannot access protected field
	}
}
