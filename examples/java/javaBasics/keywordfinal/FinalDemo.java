package com.revature.javaBasics.keywordfinal;

/*
 * Demonstrates the 'final' keyword applied to:
 *   1. A local variable (cannot be reassigned once initialized)
 *   2. A static final constant (a class-level utility value, e.g. PI)
 *   3. A final method (cannot be overridden by subclasses)
 *   4. A final class (cannot be subclassed/extended at all)
 */
public class FinalDemo {
    // static final: shared across all instances, and cannot be reassigned.
    // Commonly used for constants like PI, MAX_SIZE, etc.
    static final double PI = 3.14159;

    public static void main(String[] args) {

        // 1. final local variable
        final int radius = 5; // once assigned, radius can never be reassigned
//        radius = 10; // <-- would NOT compile: cannot assign a value to final variable 'radius'

        double area = PI * radius * radius;
        System.out.println("Circle area: " + area);

        // 2. static final constant usage
        System.out.println("PI is: " + PI);
        // PI = 3.14; // <-- would NOT compile: PI is static final, cannot be reassigned

        // 3. final method (called via a Shape instance)
        Shape shape = new Shape();
        shape.describe(); // uses the final method, which cannot be overridden by subclasses

        // 4. final class (cannot be extended)
        ImmutablePoint point = new ImmutablePoint(3, 4);
        System.out.println("Point: (" + point.getX() + ", " + point.getY() + ")");

    }

    // Example class with a final method
    static class Shape {
        // final method: subclasses can inherit this method but CANNOT override it.
        // Useful for locking in behavior that must stay consistent across all subtypes.
        final void describe() {
            System.out.println("I am a shape.");
        }

        void other() {
            System.out.println("Some implementation");
        }
    }

//     Uncomment to see the compile error a final method prevents:
     static class Circle extends Shape {
//         @Override
//         void describe() { // <-- would NOT compile: cannot override final method
//             System.out.println("I am a circle.");
//         }

         @Override
         void other() {
             System.out.println("New Implementation!");
         }
     }

    /* final class: cannot be extended by any other class.
     * Often used for immutable classes (like String, Integer) where
     * subclassing could break guarantees about immutability.
     */
    static final class ImmutablePoint {
        // final instance fields: must be assigned exactly once
        // (either at declaration or in the constructor), then never changed.
        private final int x;
        private final int y;

        ImmutablePoint(int x, int y) {
            this.x = x; // assigned once here, in the constructor
            this.y = y;
        }

        int getX() {
            return x;
        }

        int getY() {
            return y;
        }
    }

//     class ExtendedPoint extends ImmutablePoint {} // <-- would NOT compile: cannot inherit from final class
}
