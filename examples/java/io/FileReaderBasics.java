package com.revature.io;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileReaderBasics {
    public static void main(String[] args) {

        String filePathPrepend = "src/main/java/com/revature/io/files/";
        String fileName = "notes.txt";

        // try-with-resources: both streams are closed automatically,
        // even if an exception occurs during reading
        try (FileReader fileReader = new FileReader(filePathPrepend+fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            int lineNumber = 1;

            // readLine() returns null once the end of the file is reached
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(lineNumber + ": " + line);
                lineNumber++;
            }

        } catch (FileNotFoundException e) {
            // Thrown specifically when the file doesn't exist at the given path
            System.out.println("File not found: " + filePathPrepend+fileName);

        } catch (IOException e) {
            // Broader exception for other read failures (e.g. disk error, permissions)
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        // Demonstrating FileReader WITHOUT BufferedReader -- reading raw characters
        // This works, but is far less convenient for line-based text processing
        try (FileReader rawReader = new FileReader(filePathPrepend+fileName)) {
            int characterCode;
            int charactersRead = 0;

            // read() returns one character at a time as an int, or -1 at end of file
            while ((characterCode = rawReader.read()) != -1 && charactersRead < 20) {
                char character = (char) characterCode;
                System.out.print(character);
                charactersRead++;
            }
            System.out.println("\n(Stopped after 20 characters - just for demonstration)");

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}