package com.revature.modernjava.controlledinheritance;

/*
 * Mixing sealed interfaces with records can be a powerful tool
 * for defining simple data types representations for an interface
 * implementation.
 *
 * In this example, 'Result' represents the result from some operation
 * or process, which can either be a success or failure, and the
 * permitted records (Success | Failure) store data as needed.
 *
 * If all subtypes for a sealed interface (or class) are inside the
 * same source file the compiler infers the permits clause
 *
 * Note, you can also include these as needed entities within
 * the sealed entity (note this would be a better approach to
 * encapsulate all related entities):
 *
 * public sealed interface Result {
 *   record Success(String data) implements Result { }
 *   record Failure(String errorMessage) implements Result { }
 * }
 *
 * Result s = new Result.Success("Confirmed");
 * Result e = new Result.Failure("Error");
 */
public sealed interface Result { }
record Success(String data) implements Result { }
record Failure(String errorMessage) implements Result { }
