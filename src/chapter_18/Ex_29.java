package chapter_18;

import java.io.File;

public class Ex_29 {
    public static void main(String[] args) {
        printDir(new File("src"));
    }

    public static void printDir(File file) {
        System.out.println(file.getName());
        printDir(file, 0);
    }

    private static void printDir(File file, int level) {
        if (!file.isDirectory()) {
            System.out.print("Not a directory");
            return;
        }

        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            System.out.print("An I/O error occurred. Please try again.");
            return;
        }

        for (int i = 0; i < listFiles.length; i++) {
            // Print the indentation
            for (int j = 0; j < level; j++)
                System.out.print("   ");

            // Print the name
            System.out.println("+--" + listFiles[i].getName());

            // If it's a directory, proceed to print its sub-directories and files with increased order
            if (listFiles[i].isDirectory()) {
                printDir(listFiles[i], level + 1);
            }
        }
    }
}
