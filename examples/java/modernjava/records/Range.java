package com.revature.modernjava.records;

public record Range(int start, int end) {

    /*
     * The compiler generates this for you:
     *
     * public Range(int start, int end) {
     *      this.start = start;
     *      this.end = end;
     * }
     *
     * You can provide your own constructor like this:
     * public Range(int start, int end) {
     *      if (start > end) {
     *          throw new IllegalArgumentException("start must be less than or equal to end")
     *      }
     *      this.start = start;
     *      this.end = end;
     * }
     *
     * This would work, but you are repeating some code (listing the fields again, and
     * you have to manually perform the assignment).
     */

    // A compact Constructor is a constructor syntax that reduces repetition and boilerplate code

    // You don't need to list the parameters again
    public Range {
        if (start > end) {
            throw new IllegalArgumentException("start must be less than or equal to end");
        }
        // You don't need to assign fields, compiler will do that
    }
}
