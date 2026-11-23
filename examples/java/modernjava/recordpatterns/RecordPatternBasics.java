package com.revature.modernjava.recordpatterns;

public class RecordPatternBasics {
    record Example(int id, String name, Object data) { }

    public static String DecomposeExample(Object obj) {
        /*
         * Record Patterns are used to deconstruct a Record type.
         *
         * You must list each component with a compatible type
         *      id : int
         *      name: String
         *      data: Object (note: in the example below a 'String' is compatible
         *                      because String extends Object)
         *
         * Each component must be listed in order, but the binding variable name
         * is up to you.
         *
         *
         * record CustomData(int id, Account bankAccount);
         *
         * Later in your code...
         *      if (obj instanceof CustomData(int identifier, SavingsAccount account)
         *      if (obj instanceof CustomData(int identifier, CheckingAccount account)
         */
        if (obj instanceof Example(int identifier, String name, String information)) {
            return "Example: (id:"+ identifier + ", name: "+ name +", information:"+ information +")";
        }

        return "Incorrect Type";
    }

    public static void main(String[] args) {
        Example e = new Example(1, "Revature", "This is the data in Example object");

        System.out.println( DecomposeExample(e) );

        // The following shows how Record Patterns can be used with switch statements
        switch (e) {
            case Example(int identifier, String name, String information) ->
                    System.out.println("Example showing Record Patterns with Switch Statements");
            default -> System.out.println("Default to 'cover all branches' required for switch expressions");
        }
    }

    public record Envelope(Object contents) { }

    static void nullRules() {
        Object nothing = null;
        System.out.println(nothing instanceof Envelope(Object o)); // false: record patterns never match null

        Envelope empty = new Envelope(null);
        // Declared type (Object): a null component still matches, and contents is null
        System.out.println(empty instanceof Envelope(Object contents)); // true

        // Narrower, compatible type (String): a null component does NOT match
        System.out.println(empty instanceof Envelope(String text)); // false
    }
}


