package chapter_17;

import java.io.*;
import java.util.Scanner;

public class Ex_04 {
    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) throws IOException {
        // Simulate the input from command line
        args = new String[]{"Welcome.java", "Welcome.utf"};

        if (args.length != 2) {
            System.out.print("Usage: textFile, binaryFile");
            System.exit(1);
        }

        // Check if source file exists
        File textFile = new File(args[0]);
        if (!textFile.exists()) {
            System.out.println("Text file " + args[0] + " does not exist");
            System.exit(2);
        }

        // Check if target file exists
        File binaryFile = new File(args[1]);
        if (binaryFile.exists()) {
            System.out.println("Binary file " + args[1] + " already exists");
//            System.exit(3);
        }

        try (
                // Create an input stream
                Scanner input = new Scanner(textFile);
                DataOutputStream output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(binaryFile)))
        ) {
            // Continuously read a byte from input and write it to output
            while (input.hasNextLine()) {
                output.writeUTF(input.nextLine() + "\n");
            }
        }

        // Display the file size
        System.out.println("The size of " + args[0] + " is: " + textFile.length());
        System.out.println("The size of " + args[1] + " is: " + binaryFile.length());
    }
}
