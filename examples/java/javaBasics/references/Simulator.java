package com.revature.javaBasics.references;

/*
 * Pass-by-value vs Pass-by-reference
 */
public class Simulator {
    public static void main (String[] args) {
        /*
          * Java is a 'pass-by-value' language
          * When passing a method, and passing an argument, the
          * method is only given the value of the data passed.
          *
          * Methods create their own variables, within that
          * method's scope, and thus, changes made to parameters
          * within a method, do not impact the argument variables
         */
        int number = 5;
        System.out.println("changePrimitive called");
        changePrimitive(number); // this method creates separate variables (parameters) and assigns values to those
        System.out.println(number); // This will print 5, not 100

        System.out.println("new Object Created");
        Data d = new Data(1);
        System.out.println(d.id); // prints 1

        System.out.println("changeObjectId called");
        changeObjectId(d);
        System.out.println(d.id); // prints 2 - this changed the ID of my object

        System.out.println("changeObject called");
        changeObject(d);
        System.out.println(d.id); // prints 2 (same as above, not 3)
    }

    /*
     * The name of this method is not actually accurate. This doesn't
     * change the primitive given to it!
     */
    public static void changePrimitive(int value) {
        value = 100;
    }

    /*
     * Here, we can change the id of our Argument Data object,
     * however, this is because the 'data' of a reference
     * variable is a memory address. That memory address allows
     * a method to manipulate values on an argument, because the
     * method is copying the memory reference to a new reference
     * variable.
     */
    public static void changeObjectId(Data d) {
        // at least 2 ref variables are pointing to the same object in memory
        d.id = 2;
    }

    /*
     * the changeObject method is creating a NEW reference variable
     * 'd' in this method is pointing to an object. The object it is
     * pointing to, is based on the argument provided...
     *
     * The body of this method is then, creating a new object and
     * reassigning the pointer of 'd' to that new Object.
     *
     * The argument is NOT changed!
     */
    public static void changeObject(Data d) {
        d = new Data(3); // creating new object with ID 3
    }
}

// Data object for use in examples above
class Data {
    int id;

    public Data(int id) {
        this.id = id;
    }
}
