package com.revature.io;

import java.io.*;

public class SerializationBasics {
    public static void main(String[] args) {
        String filePath = "src/main/java/com/revature/io/files/user_profile.txt";
        UserProfile originalProfile = new UserProfile("jdoe", 3, "super-secret-password");
        serializeProfile(filePath, originalProfile);

        UserProfile deserialized = deserializeProfile(filePath);
        System.out.println("Deserialized user profile: " + deserialized);

        failSerialization();
    }

    public static void serializeProfile(String filePath, UserProfile originalProfile) {
        // Serializing: writing an object's state to a file using ObjectOutputStream
        try (FileOutputStream fos = new FileOutputStream(filePath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(originalProfile);
            System.out.println("Serialized: " + originalProfile);

        } catch (IOException e) {
            System.out.println("An error occurred during serialization: " + e.getMessage());
        }
    }

    public static UserProfile deserializeProfile(String filePath) {
        System.out.println("Reading Profile from file...");
        UserProfile restoredProfile = null;

        // Deserializing: reconstructing the object from the file using ObjectInputStream
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            // readObject() returns Object -- must be cast back to the original type
            restoredProfile = (UserProfile) ois.readObject();

            // Printing out the object here as a sanity test...
//            System.out.println("Deserialized: " + restoredProfile);
            // Note: password will print as "null" -- the transient field was
            // never written to the file, so it can't be restored from it

        } catch (IOException e) {
            System.out.println("An error occurred during deserialization: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Thrown if the JVM can't find the class definition for the
            // object being deserialized
            System.out.println("Class definition not found: " + e.getMessage());
        }

        return restoredProfile;
    }

    public static void failSerialization() {
        try (FileOutputStream fos = new FileOutputStream("src/main/java/com/revature/io/files/will_fail.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            // This throws NotSerializableException at runtime --
            // the compiler does NOT catch this, since it's a runtime check
            oos.writeObject(new NonSerializableExample());

        } catch (NotSerializableException e) {
            System.out.println("Cannot serialize -- class does not implement Serializable: "
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}

// A serializable class must implement Serializable
class UserProfile implements Serializable {

    // Explicit serialVersionUID -- best practice, since it gives predictable
    // control over version compatibility rather than relying on a generated one
    private static final long serialVersionUID = 1L;

    private String username;
    private int accountAge;

    // transient: this field will be excluded from serialization entirely --
    // appropriate for sensitive data that shouldn't be written to disk
    private transient String password;

    public UserProfile(String username, int accountAge, String password) {
        this.username = username;
        this.accountAge = accountAge;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserProfile{username='" + username + "', accountAge=" + accountAge
                + ", password='" + password + "'}";
    }
}

// Showcasing the wrong way (educational purposes only):
// Attempting to serialize a class that does NOT implement Serializable
class NonSerializableExample {
    String data = "This will fail to serialize";
}