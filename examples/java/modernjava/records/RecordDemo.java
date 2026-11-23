package com.revature.modernjava.records;

import java.util.HashSet;
import java.util.Set;

public class RecordDemo {
    public static void main(String[] args) {
        PersonRecord amy = new PersonRecord("Amy", 35);

        // 'Getter' methods for Records just use the name of the component
        // field - not 'getFieldName()'
        System.out.println(amy.name());     // 'Amy'
        System.out.println(amy.age());      // 35

        // prints the toString() implementation
        System.out.println(amy);            // PersonRecord[name=Amy, age=35]

        PersonRecord alsoAmy = new PersonRecord("Amy", 35);
        System.out.println(amy.equals(alsoAmy));    // true -> equals() implementation
        System.out.println(amy == alsoAmy);         // false -> different objects in memory

        // Records are reliable map/set keys since equals and hashCode stay consistent
        Set<PersonRecord> people = new HashSet<>();
        people.add(amy);
        people.add(alsoAmy);
        System.out.println(people.size());      // 1 (the objects produce the same equals/hashCode
    }
}
