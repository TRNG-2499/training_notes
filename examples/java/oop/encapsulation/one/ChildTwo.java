package com.revature.oop.encapsulation.one;

public class ChildTwo extends Parent {

    public void printMyFields() {
        System.out.println(this.pubString);
        System.out.println(this.proString);
        System.out.println(this.defString);
//        System.out.println(this.priString); // not visible


        this.pubMethod();
        this.proMethod();
        this.defMethod();
//        this.priMethod(); // not visible

        Parent p = new Parent();

        System.out.println(p.pubString);
        System.out.println(p.proString);
        System.out.println(p.defString);
//        System.out.println(p.priString);
    }


}
