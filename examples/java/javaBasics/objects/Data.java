package com.revature.javaBasics.objects;

/*
 * The Object class is the implicit superclass of all
 * classes in Java
 */
public class Data extends Object {
    public static void main(String[] args) {
        // Invoking the Constructor of 'Data' to create an instance of the Data Object
        Data d_1 = new Data("Joseph", 1, 100.0);
        d_1.doSomething();  // invoking a method on the instance object (d_1)
        System.out.println( d_1.amount ); // accessing a field on the object
        System.out.println(d_1);

        // A second instance of the 'Data' object
        Data d_2 = new Data("Joseph", 1, 100.0);
        System.out.println(d_2);

        System.out.println("D_1 == D_2: " + (d_1 == d_2));  // False - not the same object in memory
        System.out.println("D_1.equals(D_2): " + d_1.equals(d_2)); // True - based on my implementation of this method

        Data d_3 = new Data();
        System.out.println( d_3.amount ); // 0 - accessing a field that was not initialized
                                            // so Java gives it an initial value
                                            /*
                                             * initial values given by Java:
                                             * primitives : 0
                                             * objects: null
                                             */
        System.out.println( d_3.name ); // null
    }

    public Data() {}

    /*
     * Methods are functions that exists within a class ("belong to a class")
     *
     * Methods must declare a return type, and must use the return keyword
     * with a matching datatype expression (unless the method uses 'void'
     * which means it doesn't return anything
     */
    public void doSomething() {
        System.out.println("This does nothing!");
        return; // technicaly we can still include the return keyword in a
                // method that returns void
    }

    public int giveMeNumber(String param) {
        System.out.println("param has: " + param.length() + " characters");

        return param.length();
    }

    // Fields
    public String name;
    public long id;
    public double amount;

    // Data class Constructor
    public Data(String name, long id, double amount) {
        this.name = name;
        this.id = id;
        this.amount = amount;
    }

    /*
     * The following is NOT an example of proper hashCode,
     * but simply provided to showcase how we can override
     * this method. The HashCode method should return
     * the same integer number for two objects that are
     * 'equal' according to the equals method implementation.
     *
     * The '@Override' annotation is used to communicate
     * that the following method is changing behavior that
     * is inherited from a parent class (the Object class
     * in this case)
     *
     * Note: an annotation is a metadata tag that may be
     * placed prior to the declaration of a class, method
     * or variable to provide context or typically have a
     * programmatic implication.
     */
    @Override
    public int hashCode() {
        int result = (int)this.id + (int)this.amount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the memory address is the same
        if (this == obj)
            return true;

        // Check if the argument is a null reference
        if (obj == null)
            return false;

        // Check if argument is Not of type 'Data'
        if (!(obj instanceof Data))
            return false;

        // Polymorphic reference. More on this later...
        Data d = (Data)obj;

        if (d.amount != this.amount)
            return false;
        if (d.id != this.id)
            return false;
        if (!d.name.equals(this.name))
            return false;

        return true;
    }

    /*
     * The toString method is called automatically by
     * System.out.prinln when an object reference
     * is detected by Java. If you do not provide an
     * overridden version of the toString method, then
     * the hashCode of an object will print instead.
     * By default, this hashcode relates to the memory
     * address of the object in question.
     *
     * Note that the use of the keyword 'this' in the
     * toString method below is not required. It is
     * included to clarify which variables 'name',
     * 'id' and 'amount' are referring to.
     */
    public String toString() {
        return "This is my toString() method: Data [name=" + this.name +
                ", id=" + this.id +
                ", amount=" + this.amount + "]";
    }

    /*
     * main() {
     *      data.Example(); // first one
     *      data.Example(5); // second one
     *      data.Example("five"); // third one
     * }
     */
    // Examples of Overloaded methods:
    public void Example() { }

    public void Example(double a) { }

    public void Example(String s) {}

    public void Example(long l) { }

    public void Example(double d, String s) { }
    public void Example(String s, double d) { }

}

