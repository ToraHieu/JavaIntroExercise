package chapter_18;

import java.io.File;
import java.util.Scanner;

public class DirectorySize {
    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

//        long start = System.nanoTime();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
//        long end = System.nanoTime();
//        System.out.println(end - start);
    }

    public static long getSize(File file) {

        long size = 0; // Store the total size of all files

        if (file.isDirectory()) {
            File[] files = file.listFiles(); // All files and subdirectories
            for (int i = 0; files != null && i < files.length; i++) {
                size += getSize(files[i]);
            }
        }
        else { // Base case
            size += file.length();
        }


        return size;
    }
}
