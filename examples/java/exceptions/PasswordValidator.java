package com.revature.exceptions;

public class PasswordValidator {

    public static boolean validatePassword(String password) {
        // Check if the password follows an expected format (capital letters, number, etc...)
        boolean criteriaMet = false;
        if (criteriaMet) {

        } else {
            throw new ImproperPasswordFormatException();
        }

        return true; // we have a valid password
    }
}

class ImproperPasswordFormatException extends RuntimeException {

}