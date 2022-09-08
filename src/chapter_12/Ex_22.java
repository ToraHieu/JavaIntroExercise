package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/** Copied and modified from Ex_16 */
public class Ex_22 {
    public static void main(String[] args) throws Exception {
        // Check command line parameter usage
        if (args.length != 3) {
            System.out.println("Usage: dir oldString newString");
            System.exit(1);
        }

        // Check if the directory exists
        File directory = new File(args[0]);
        if (!directory.exists() && !directory.isDirectory()) {
            System.out.println("Source directory " + args[0] + " does not exist");
            System.exit(2);
        }
        
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].canWrite()) {
                replaceText(files[i], args[1], args[2]);
            }
        }
        System.out.println("Complete.");
    }
    
    private static void replaceText(File file, String oldString, String newString) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        try (
                // Read text from the file and save it in a string builder
                Scanner input = new Scanner(file);) {
            while (input.hasNext()) {
                String s1 = input.nextLine();
                String s2 = s1.replaceAll(oldString, newString);
                sb.append(s2 + "\r\n");
            }
        }

        try (
                // Write back to the file
                PrintWriter output = new PrintWriter(file);) {
            output.printf("%s\r\n", sb.toString());
        }
    }
    
}
