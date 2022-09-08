package chapter_18;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Ex_31 {
    public static void main(String[] args) {
        // Stimulate command line
        args = new String[]{"testDir", "Tora", "HN"};

        if (args.length != 3) {
            System.out.print("Incorrect input. Usage: dirName oldWord newWord");
            System.exit(1);
        }

        File file = new File(args[0]);
        if (file.exists())
            replaceWord(file, args[1], args[2]);
        else {
            System.out.print("File does not exist");
            System.exit(0);
        }
    }

    public static void replaceWord(File file, String oldWord, String newWord) {
        // If it's a file, search for the oldWord's occurrances in it.
        if (file.isFile()) {
            try {
                Scanner input = new Scanner(file);
                StringBuilder content = new StringBuilder();
                while (input.hasNextLine()) {
                    String line = input.nextLine();
                    content.append(line.replaceAll(oldWord, newWord)).append("\n");
                }
                input.close();
                PrintWriter output = new PrintWriter(file);
                output.print(content);
                output.close();
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
        for (File currentFile : listFiles) replaceWord(currentFile, oldWord, newWord);
    }
}
