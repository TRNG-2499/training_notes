package com.revature.streams.basics;

import java.util.List;
import java.util.function.*;

/*
 * YOU DON'T NEED TO MEMORIZE ANY OF THE FUNCTIONAL INTERFACES BELOW!
 *
 * Java provides a number of built-in functional interfaces that are used in Functional programming
 * with Java.
 *
 * The following is an examination of some of the common functional interfaces which are used:
 *
 * interface Supplier<T> { T get(); } - Supplier creates an instance of the specified type.
 *
 * interface Consumer<T> { void accept(T); } - Consumer performs some action and accepts a single argument of any type
 *
 * interface BiConsumer<T, U> { void accept(T t, U u); } - BiConsumer performs some action and accepts two arguments of any type
 *
 * interface Predicate<T> { boolean test(T t); } - Predicate returns a boolean and accepts an argument of any type
 *
 * interface BiPredicate<T, U> { boolean test(T t, U u); } - BiPredicate returns a boolean and accepts two arguments of any type
 *
 * interface Function<T, R> { R apply(T t); } - Function performs some action and accepts any single type (T) and returns any type (R)
 *
 * interface BiFunction<T, U, R> { R apply(T t, U u); } - BiFunction performs some action and accepts any two types (T, U) and returns any type (R)
 *
 * interface UnaryOperator<T> { T apply(T t); } - UnaryOperator performs some action and accepts any type and returns an instance of a matching type
 *
 * interface BinaryOperator<T> { T apply(T t1, T t2); } - BinaryOperators performs some action and accepts two arguments of a matching type and
 * 														returns an instance of a matching type as well.
 */
public class FunctionalInterfacesOverview {
    public static void main(String[] args) {

        // ---- Supplier<T>: T get() ----
        // Takes no arguments, produces a value.
        Supplier<String> greetingSupplier = () -> "Hello, functional Java!";
        System.out.println("Supplier: " + greetingSupplier.get());

        // ---- Consumer<T>: void accept(T) ----
        // Takes an argument, returns nothing, performs a side effect.
        Consumer<String> printer = s -> System.out.println("Consumer: " + s.toUpperCase());
        printer.accept("consumed value");

        // ---- BiConsumer<T, U>: void accept(T, U) ----
        // Takes two arguments, returns nothing.
        BiConsumer<String, Integer> printRepeated =
                (s, times) -> System.out.println("BiConsumer: " + s.repeat(times));
        printRepeated.accept("ab", 3);

        // ---- Predicate<T>: boolean test(T) ----
        // Takes an argument, returns a boolean.
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Predicate: 7 is even? " + isEven.test(7));

        // ---- BiPredicate<T, U>: boolean test(T, U) ----
        // Takes two arguments, returns a boolean.
        BiPredicate<String, Integer> isLongerThan =
                (s, len) -> s.length() > len;
        System.out.println("BiPredicate: 'hello' longer than 3? " + isLongerThan.test("hello", 3));

        // ---- Function<T, R>: R apply(T) ----
        // Takes one type, returns a (possibly different) type.
        Function<String, Integer> stringLength = String::length;
        System.out.println("Function: length of 'functional' = " + stringLength.apply("functional"));

        // ---- BiFunction<T, U, R>: R apply(T, U) ----
        // Takes two (possibly different) types, returns a (possibly different) type.
        BiFunction<String, String, Integer> combinedLength =
                (a, b) -> (a + b).length();
        System.out.println("BiFunction: combined length = " + combinedLength.apply("foo", "barbaz"));

        // ---- UnaryOperator<T>: T apply(T) ----
        // Special case of Function<T, T> — input and output types match.
        UnaryOperator<Integer> square = n -> n * n;
        System.out.println("UnaryOperator: square of 5 = " + square.apply(5));

        // ---- BinaryOperator<T>: T apply(T, T) ----
        // Special case of BiFunction<T, T, T> — both inputs and output types match.
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("BinaryOperator: 4 + 6 = " + sum.apply(4, 6));

        // ---- Putting a few together: a tiny pipeline ----
        List<String> names = List.of("Alice", "bob", "Charlie", "dave");

        Predicate<String> startsWithUpper = s -> Character.isUpperCase(s.charAt(0));
        Function<String, String> shout = s -> s.toUpperCase() + "!";
        Consumer<String> report = s -> System.out.println("Pipeline: " + s);

        names.stream()
                .filter(startsWithUpper)   // Predicate
                .map(shout)                // Function
                .forEach(report);          // Consumer
    }
}
