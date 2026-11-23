package com.revature.modernjava.records;

/*
 * The compiler generates private fields, a constructor, accessors,
 * equals, hashCode and toString methods
 *
 * Note: Setters are not provided since the fields are final
 */
public record PersonRecord(String name, int age) { }
