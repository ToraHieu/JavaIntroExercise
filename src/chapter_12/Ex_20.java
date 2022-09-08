package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/** Copied and modified from Ex_18 */
public class Ex_20 {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: srcRootDirectory");
            System.exit(1);
        }

        File srcRootDirectory = new File(args[0]);
        if (!srcRootDirectory.exists()) {
            System.out.println("The directory does not exist.");
            System.exit(2);
        }

        if (!srcRootDirectory.isDirectory()) {
            System.out.println("This is not a directory.");
            System.exit(3);
        }
        
        File[] files = srcRootDirectory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory() && files[i].getName().startsWith("chapter")) {
                removeFromFilesInDirectory(files[i]);
            }
        }
    }
    
    private static void removeFromFilesInDirectory(File directory) throws FileNotFoundException {
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].getName().endsWith(".java")) {
                removePackageStatement(files[i]);
            }
        }
    }
    
    private static void removePackageStatement(File fileName) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(fileName)) {
            String firstLine = scanner.nextLine();
            if (!firstLine.startsWith("package")) {
                // If the file doesn't have the package statement, exit the method without touching it.
                return;
            }
            while (scanner.hasNext()) {
                content.append(scanner.nextLine() + "\r\n");
            }
        }
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.print(content.toString());
        }
    }
}
