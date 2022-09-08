package chapter_18;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ex_30 {
    public static void main(String[] args) {
        // Stimulate command line
        args = new String[]{"src", "int"};

        if (args.length != 2) {
            System.out.print("Incorrect input. Usage: dirName word");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (file.exists())
            findOccurrences(new File(args[0]), args[1]);
        else {
            System.out.print("File does not exist");
            System.exit(2);
        }
    }

    public static void findOccurrences(File file, String word) {
        // If it's a file, search for the word's occurrances in it.
        if (file.isFile()) {
            try {
                Scanner input = new Scanner(file);
                int lineCount = 0;
                while (input.hasNextLine()) {
                    lineCount++;
                    String line = input.nextLine();
                    // Found the occurrence.
                    for(String w: line.split(" ")) {
                        if(w.trim().equals(word))
                            System.out.println(word + " is found in " + file.getPath() + " at line " + lineCount + ".");
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return;
        }

        // It's a directory by here, search for occurrences in the files in it.
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            System.out.print("An I/O error occurred. Please try again.");
            return;
        }
        for (File currentFile : listFiles) findOccurrences(currentFile, word);
    }
}
