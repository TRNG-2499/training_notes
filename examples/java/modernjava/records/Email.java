package com.revature.modernjava.records;

/*
 * You can provide additional functionality within a Record as needed
 */
public record Email(String address) {

    // Compact Constructor
    // This will still take all components as arguments when called
    // but gives you the ability to have some logic in your constructor
    // such as data validation!
    public Email {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be blank");
        }

        // Normalize the address parameters (all lowercase, and remove any trailing whitespace)
        address = address.trim().toLowerCase();
    }

    // This returns everything after the '@' in the email address
    // i.e. 'person@gmail.com' -> 'gmail.com'
    public String domain() {
        return address.substring(address.indexOf('@') + 1);
    }
}
