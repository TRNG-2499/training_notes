package com.revature.streams.pipeline;

import java.util.Comparator;
import java.util.stream.Stream;

/*
 * Intermediate operations produce a new stream as the result. Unlike Terminal Operations, Intermediate operations
 * have more flexibility when working with infinite streams, since they can return another infinite stream as the
 * result (this works because elements are produced as needed from infinite streams).
 *
 * Common Intermediate Operations:
 *
 * Stream<T> filter(Predicate<? super T> predicate) - returns a Stream with elements that match a given expression.
 *
 * Stream<T> distinct() - returns a stream with all duplicate values removed.
 *
 * Stream<T> limit(long maxSize) - returns up to the specified number of elements from a stream.
 *
 * Stream<T> skip(long n) - returns a stream after skipping the specified number of elements.
 *
 * 		Note: limit() & skip() are particularly useful for turning infinite streams into finite streams
 *
 * <T> Stream<R> map(Function<? super T, ? extends R> mapper) - creates a 1-to-1 mapping from elements in the stream to elements in
 * 																the next step of the stream. Essentially, it can be used to examine
 * 																some property of each element in a stream.
 *
 * sorted() - returns a stream with the elements sorted. There are two method signatures, one which uses natural ordering, and another
 * 			which takes a Comparator so that sorting order can be specified.
 *
 *  	Stream<T> sorted()
 *  	Stream<T> sorted(Comparator<? super T> comparator)
 *
 *
 * Stream<T> peek(Consumer<? super T> action) - performs actions on a stream and returns a stream which is, for all intents and
 * 												purposes, unaltered. It is useful for debugging
 */
public class IntermediateOps {
    public static void main(String[] args) {
        Stream<String> s1 = Stream.of("Apple", "Avocado", "Banana", "Blueberry", "Carrot", "Coconut");

        System.out.println("FILTER");
        s1.filter(
                        s -> s.startsWith("A"))
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("DISTINCT");
        Stream<String> s2 = Stream.of("Blueberry", "Apple", "Banana", "Blueberry",
                "Blueberry", "Blueberry", "Coconut","Apple");
        s2.distinct()
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("SKIP (3)");
        Stream<String> s3 = Stream.of("Apple", "Avocado", "Banana", "Blueberry", "Carrot", "Coconut");
        s3.skip(3)
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("LIMIT");
        Stream<String> s4 = Stream.of("Apple", "Avocado", "Banana", "Blueberry", "Carrot", "Coconut");
        s4.limit(4)
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("SKIP + LIMIT");
        Stream<Integer> infinite = Stream.iterate(1, n -> n+2);
        infinite
                .skip(10)
                .limit(5)
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("MAP");
        Stream<String> s5 = Stream.of("Apple", "Avocado", "Banana", "Blueberry", "Carrot", "Coconut");
        s5.map(s -> s.length())
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("SORTED");
        Stream<String> s6 = Stream.of("Avocado", "Blueberry", "Coconut", "Carrot","Banana","Apple");
        s6.sorted()
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("SORTED [REVERSE]");
        Stream<String> s7 = Stream.of("Avocado", "Blueberry", "Coconut", "Carrot","Banana","Apple");
        s7.sorted( Comparator.reverseOrder() )
                .forEach(a -> System.out.print(a + ","));

        System.out.println();

        System.out.println("COUNT (SHOWING MULTIPLE TOGETHER) | FILTER (B) -> PRINT 2 (banana, blueberry) -> COUNT TOTAL");
        Stream<String> s8 = Stream.of("Apple", "Avocado", "Banana", "Blueberry", "Carrot", "Coconut");
        long count = s8
                .filter(x -> x.startsWith("B"))
                .peek(a -> System.out.print(a + ","))
                .count();
        System.out.println("\n"+count);

    }
}
