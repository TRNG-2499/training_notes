package com.revature.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;

public class FileIOExample {
    /*
     * FileInputStream and FileOutputStream read and write bytes of data from a file.
     * Meant for consuming bytes of data, not characters
     */
    static FileInputStream fis;
    static FileOutputStream fos;
    static ArrayList<Integer> fileData = new ArrayList<>();

    static String filePathPrepend = "src/main/java/com/revature/io/files/";

    public static void main(String[] args) throws IOException {
        readImageFile();
        copyImageToNewFile();
        copyAndCorruptImage();
    }

    public static void readImageFile() throws IOException {
        fis = new FileInputStream(filePathPrepend+"cat.jpg");
        int i = 0;
        while ((i = fis.read()) != -1) {
            fileData.add(i); // add every byte of data to an array
        }
        System.out.println("Finished reading");
        System.out.println("# bytes: "+fileData.size()); // let's see the size of the data (how many bytes?)
        fis.close();
    }

    public static void copyImageToNewFile() throws IOException {
        System.out.println("Writing new image to file...");
        fos = new FileOutputStream(filePathPrepend+"newCat.jpg");
        for (int i=0;i<fileData.size();i++) {
            fos.write(fileData.get(i)); // we are copying the image to a new file!
        }
        System.out.println("Finished!");
        fos.close();
    }

    public static void copyAndCorruptImage() throws IOException {
        System.out.println("Copying a corrupted version of the file...");
        fos = new FileOutputStream(filePathPrepend+"corruptedCat.jpg");
        for (int i=0;i<fileData.size();i++) {

            // We are NOT destroying some of the initial bytes of the image
            if (i < fileData.size()/4)
                fos.write(fileData.get(i));
            else
                fos.write(fileData.get(i)+1); // now we are corrupting the image!
        }
        System.out.println("Finished!");
        fos.close();
    }
}
