package com.revature.lambdas;

import com.revature.lambdas.implementors.HelloWorldImplementor;
import com.revature.lambdas.interfaces.HelloWorldInterface;

import java.util.ArrayList;
import java.util.List;

public class LambdaDriver {

    // This 'sayHi' method expects an implementation of my HelloWorldInterface
    // We can provide this implementation in a few ways:
    // 1. Create a class that implements the interface, construct the object with that class, and pass that to our method
    // 2. Create a lambda as a reference variable and pass that lambda ref var to the method
    // 3. Call the method and pass a lambda implementation directly!
    public static void sayHi(HelloWorldInterface hello) {
        hello.sayHello();
    }

    public static void main(String[] args) {
        /*
         * To use an interface, we need to create an instance of a
         * class, which implements that interface
         */
        HelloWorldInterface hwiObj = new HelloWorldImplementor();
        sayHi(hwiObj);

        /*
         * A Lambda allows you to create an in-line implementation
         * of a functional interface
         *
         * Lambdas when created, are placed on the stack
         */
        HelloWorldInterface lambda =
                () -> {
            System.out.println("Hello World - Lambda!");
        };

        sayHi(lambda);
        lambda.sayHello();


        /*
         * Furthermore, you can pass this in-line implementation
         * directly to a method call
         *
         * Both of the following are doing the same thing!
         */
        sayHi(
                lambda
        );

        // By passing a Lambda implementation as the argument of a method
        // you are only using that implementation one time!
        // Saving the implementation of a lambda to a reference variable
        // would mean that you expect to use that implementation later!
        sayHi(
                () -> {
                    System.out.println( "Hello World implementation as an argument!" );
                }
        );



        // This example is a more "realistic" look at how Lambdas are used in application code
        User u = new User("Revature", "Pass123");
        validateUser( u ,

            (User input) -> {
            return input.uName.equals("Revature") &&
                    input.uPass.equals("Pass123");
            });



    }


    /*
        In this example, the validateUser method takes both a User
        and a Validator. If you look below, the Validator functional
        interface has a single method (validate) that accepts a User
        as the argument for the method call.

        In this way, our validateUser passes the User argument given
        to our Validator implementation to return the result of that
        validate method's implementation!
     */
    public static boolean validateUser(User u, Validator v) {
        return v.validate(u);
    }



} // End of Main Class

/*
 * The following Class and Interface are used to showcase how Lambdas
 * are typically be used when expecting implementation for a Class
 */
class User {
    public String uName;
    public String uPass;
    public User(String uName, String uPass) {
        this.uName = uName;
        this.uPass = uPass;
    }
}

/*
 * This 'Validator' implementation should 'validate' a user - perhaps from the database
 */
@FunctionalInterface
interface Validator {
    public boolean validate(User u);
}