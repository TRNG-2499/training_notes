package com.revature.modernjava.records;

/*
 * Example showcasing a Traditional Java Class, which has the same
 * features/functionality that is provided implicitly by a Record
 *
 * see PersonRecord.java for more details
 */

import java.util.Objects;

// We make this class final, because that is implicit for Records
public final class PersonClass {
    private final String name;
    private final int age;

    public PersonClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Note: This is the traditional Getter naming convention
    // Records would just use the field's name (i.e. name() & age())
    public String getName() { return name; }
    public int getAge() { return age; }

//    public void setName(String name) {
//        this.name = name;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonClass other = (PersonClass) o;
        return this.age == other.age && this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "PersonClass[" +
                "name='" + name + '\'' +
                ", age=" + age +
                ']';
    }
}
