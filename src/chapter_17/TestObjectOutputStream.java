package chapter_17;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TestObjectOutputStream {
    public static void main(String[] args) throws IOException {
        try (// Create an output stream for file  object.dat
             ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("Object.dat"))
        ) {
            // Write a string, double value, and object to the file
            output.writeUTF("John");
            output.writeDouble(85.5);
            output.writeObject(new java.util.Date());
        }
    }
}
