package com.revature.lambdas.methodreference;

import com.revature.lambdas.interfaces.HelloWorldInterface;

/*
 * Method references are a short-lived, in-line implementation of
 * a Functional interface, which is achieved by leveraging a method
 * which has already been defined.
 *
 * Lambdas: You define the implementation in-line
 * Method References: The implementation has been defined previously
 *
 * Anywhere you can use a lambda, you could (in theory) replace it with
 * a method reference.
 *
 * Method Reference Syntax:
 *      Class|Reference::methodName|new
 *          ex:
 *      System.out::println // here 'out' is a reference to a print stream. 'println' is the method
 *      Object::new
 *
 * There are four (4) types of method references:
 *      1. Static Methods
 *      2. Instance methods on a particular instance in our code
 *      3. Constructors
 *      4. Instance methods on a parameter (determined at runtime)*
 *
 * NOTE: The fourth type of method reference is fairly confusing, compared to the others
 * Make sure that you first review the other three types - you won't need to understand
 * the fourth type unless you are either going for a Java Certification, or want a deeper
 * understanding of method references on your own.
 *
 * Method References heavily rely on previous implementations of code. Effectively a method
 * reference is calling code that *already exists somewhere*, which can satisfy the input/output
 * implementation of a functional interface
 */
public class Driver {

    /*
     * This method uses our Functional Interface to allow us to swap the intended
     * implementation of this operation (get a number given a string input) as  needed
     */
    public static int getNumberFromStringInput(StringToNum impl, String s) {
        return impl.convert(s);
    }

    /*
     * For the following examples, we are providing an implementation for the 'StringToNum'
     * functional interface. That Functional interface Method looks like the following:
     *      public Integer convert(String input)
     *
     * The 'constructor' example is leveraging the 'ObjectMaker' functional interface
     * which looks like this:
     *      public T makeObject();
     */
    public static void main(String[] args) {
        // A lambda would look like this:
        String input = "hello";
        getNumberFromStringInput(
                (String s) -> {
                    return s.length();
                    },
                input
        );

        // This is still a lambda! - this just shows that our implementation could be
        // anything, so long as it returns a number because that satisfies our Functional
        // Interface method's implementation
        getNumberFromStringInput(
                (s) -> {
                    int vowels = 0;
                    for (int i = 0; i < s.length(); i++) {
                        switch (s.charAt(i)) {
                            case 'a':
                            case 'e':
                            case 'i':
                            case 'o':
                            case 'u':
                                vowels += 1;
                        }
                    }
                    return vowels;
        },input);

        // Method Reference | Calling a Static Method on a class
        getNumberFromStringInput(
                // For static methods the syntax is: ClassName::StaticMethodName
                MyStaticUtilityClass::countVowels,
                input);


        // Method Reference | Instance Method on an instance variable
        MyInstanceUtilityClass instance = new MyInstanceUtilityClass();
        getNumberFromStringInput(
                // referenceVariableName::instanceMethodName
                instance::firstCharacterInt,
                input);

        // Constructor syntax:
        // This ONLY CALLS THE NO-ARGS CONSTRUCTOR FOR A CLASS!
        // Syntax: ClassName::the-keyword-'new' | ClassName::new
        ObjectMaker<Object> objGen = Object::new;
        ObjectMaker<MyStaticUtilityClass> staticUtil = MyStaticUtilityClass::new;
        ObjectMaker<MyInstanceUtilityClass> instanceUtil = MyInstanceUtilityClass::new;


        // Method Reference | Instance method on parameter
        // THIS IS THE FOURTH METHOD YOU DON'T NEED TO KNOW
        /*
         * The 'String' class has a method which returns
         * a number (length). The implementation for the
         * StringToNum interface is calling the length
         * method on the given input value (in this case
         * the argument 'input')
         */
        getNumberFromStringInput(String::length, input);
    }

}

/*
 * This class contains a static method which can be used as an implementation
 * for the 'StringToNum' functional interface. As such, we can use a static
 * method reference to call this class's method
 */
class MyStaticUtilityClass {
    public MyStaticUtilityClass() {}

    public static int countVowels(String input) {
        int vowels = 0;

        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    vowels += 1;
            }
        }

        return vowels;
    }
}

/*
 * This class also has a method which can be used to satisfy the
 * implementation of our StringToNum class
 */
class MyInstanceUtilityClass {
    public int firstCharacterInt(String input) {
        if (input.length() == 0) return 0;

        return input.charAt(0);
    }
}