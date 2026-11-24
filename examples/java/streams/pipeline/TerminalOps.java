package com.revature.streams.pipeline;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * Some Terminal operations are known as 'Reductions'. Reductions are a special type of terminal operation
 * 		in which all the contents of the stream are combined into a single primitive or Object.
 *
 * Common Terminal Operations:
 *
 * long count() - determines the number of elements of a finite stream.
 * 			count() returns a long
 * 			count() is a Reduction
 *
 * Optional<T> min(Comparator<? super T> comparator) - finds the minimum value based on the sort order
 * 														of the supplied Comparator.
 * Optional<T> max(Comparator<? super T> comparator) - finds the maximum value based on the sort order
 * 														of the supplied Comparator.
 * 		min() & max() return an Optional<T>
 * 		min() & max() are Reductions
 *
 * Optional<T> findAny() - Returns an element of the stream, unless it is empty, then it returns an empty Optional.
 * 							Typically, findAny returns the first element, but this is not guaranteed behavior.
 * Optional<T> findFirst() - Returns the first element of a stream, unless it is empty, then it returns an empty Optional.
 * 		findAny() & findFirst are NOT reductions
 *
 * boolean allMatch(Predicate <? super T> predicate) - searches the returns and returns true if all data pertains to the predicate.
 * boolean anyMatch(Predicate <? super T> predicate) - searches the returns and returns true if any data pertains to the predicate.
 * boolean noneMatch(Predicate <? super T> predicate) - searches the returns and returns true if no data pertains to the predicate.
 * 		allMatch(), anyMatch() & noneMatch return booleans
 * 		allMatch(), anyMatch() & noneMatch are NOT reductions
 *
 * void forEach( Consumer<? super T> action ) - performs some action with each element of the stream. Does not return any values.
 * 		forEach() has no return type
 * 		forEach() is NOT a reduction
 *
 * reduce() - combines a stream into a single object. There are three (3) method signatures, but we will only examine two.
 *
 * 		T reduce(T identity, BinaryOperator<T> accumulator)
 *
 * 		Optional<T> reduce(BinaryOperator<T> accumulator)
 *
 * 		reduce() returns different types depending on the method signature used
 * 		reduce() is a reduction
 *
 * collect() - combines a stream into a new object of a different type. Collect() combines values into a mutable object, and is typically
 * 				used with objects like StringBuilder and ArrayList. There are two (2) method signatures, but we'll only examine one.
 *
 * 		<R,A> R collection(Collection<? super T, A, R> collector)
 *
 * 		collect() returns a specified type.
 * 		collect() is a special type of reduction known as a mutable reduction.
 */
public class TerminalOps {
    public static void main(String[] args) {
        Stream<Integer> s1 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println(s1.count()); // counts the number of elements in the collection.

        Stream<Integer> s2 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println( s2.min( (a,b) -> a - b ).get() ); // prints the lowest value of the stream

        Stream<Integer> s3 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println(s3.max( (a,b)-> a-b ).get() ); // prints the highest value of the stream

        Stream<Integer> s4 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println( s4.allMatch(n -> n % 2 == 0) ); // returns true of all stream elements are even

        Stream<Integer> s5 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println( s5.anyMatch( n -> n % 2 == 0) ); // returns true if at least one stream element is even

        Stream<Integer> s6 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println( s6.noneMatch( n -> n % 2 == 0) ); // returns true if no stream element is even

        Stream<Integer> s7 = Stream.of(1,2,3,4,5,6,7,8,9,10);
        s7.forEach(System.out::print); // prints each element using System.out.print

        System.out.println();
        Stream<String> s8 = Stream.of("R","E","V","A","T","U","R","E");
        System.out.println(s8.reduce("", (c1, c2) -> c1+c2) ); // concatenate values to an initial String over time...

        Stream<String> s9 = Stream.of("R","E","V","A","T","U","R","E");
        System.out.println(s9.reduce( (c1, c2) -> c1+c2 ).get() ); // This one returns an Optional, and does not allow you to specify the identity...

        Stream<String> s10 = Stream.of("R","E","V","A","T","U","R","E");
        Set<String> set = s10.collect(Collectors.toSet());
        System.out.println(set);
    }
}

