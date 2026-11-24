package com.revature.streams.optional;

// Be sure to import Optional from java.util
import java.util.Optional;

/*
 * The Optional class exists to provide an alternative value to 'null' or 'zero'
 * 		when supplying potentially incomplete data from your program.
 *
 * "Real world" example:
 * 		You take a university class and score 80 and 100 on the first two tests, so your average would be 90%.
 * 		However, if you start a new class on the first day and I ask what is your average what would be your answer?
 * 			'zero' sounds really bad, and isn't necessarily accurate.
 *  		'null' doesn't really make sense, are you not taking the class, or could indicate an error in the program.
 *  		An 'Empty' optional can mean: "we don't know" | "we don't have that information" | "not applicable"
 *
 * A optional can best be described as "A box which might have something in it, or it might be empty".
 */
public class OptionalBasics {
    // Non-Optional Version (not great!):
    public static Double badAverage(int... scores) {
        Double ret = (double) 0;
        if (scores.length > 0) {
            int total = 0;
            for (int i : scores)
                total += i;

            return ret = (double) total/scores.length;
        }
        return null;
    }


    // Optional Version (better!):
    public static Optional<Double> average(int... scores) {
        // Optional.empty() is used to produce an empty Optional
        if (scores.length == 0) return Optional.empty();

        int total = 0;
        for (int i : scores) total += i;

        // Optional.of() is used to supply a value to be returned from the Optional
        return Optional.of((double) total/scores.length);
    }





    public static void main(String[] args) {
        // We don't need optionals, but they provide us with more flexibility
        Double d1 = badAverage();
        if (d1 != null) {
            System.out.println(d1);
        } else {
            System.out.println("No values provided");
        }


        Optional<Double> o1 = average(80,100);
        // the 'isPresent' method checks if the Optional has data!
        if (o1.isPresent())
            System.out.println(o1.get()); // prints 90

        Optional<Double> o2 = average();
        if (o2.isPresent())
            System.out.println(o2.get()); // this is skipped - optional is empty so o2.isPresent() is false

        // without the if statement we get a NoSuchElementException...
        System.out.println(o2.get()); // This get() is called on an Empty Optional, so we get an exception
    }







    // it is very common to use empty() and of() to generate an optional or an empty value, such as:
    private Optional<Object> getOpt (Object... values) {
        Optional<Object> o = (values.length == 0) ? Optional.empty() : Optional.of(values);

        // this is so common that the 'ofNullable()' factory method is given which does essentially the same thing:
        // Optional<Object> opt = Optional.ofNullable(values);
        return o;
    }
}
