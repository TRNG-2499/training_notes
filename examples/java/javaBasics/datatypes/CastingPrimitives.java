package com.revature.javaBasics.datatypes;

public class CastingPrimitives {
    public static void main(String[] args) {

        int smallerNumber = 5;
        long bigNumber = 100L;
        /*
         * Casting allows you to convert between datatypes.
         *
         * Java will automatically cast smaller datatypes into
         * larger containers.
         *
         * If you try to store a larger datatype into a smaller
         * container, then you need to explicitly cast (use the
         * cast operator).
         */
        bigNumber = smallerNumber;

        smallerNumber = (int)bigNumber; // requires an explicit cast
    }
}
