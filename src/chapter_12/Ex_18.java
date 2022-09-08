package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/** Had reviewed the solution file before completing because I got stuck. */
public class Ex_18 {
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
                String packageIndex = files[i].getName();
                addToFilesInDirectory(files[i], packageIndex);
            }
        }
    }
    
    private static void addToFilesInDirectory(File directory, String packageIndex) throws FileNotFoundException {
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile() && files[i].getName().endsWith(".java")) {
                addPackageStatement(files[i], packageIndex);
            }
        }
    }
    
    private static void addPackageStatement(File fileName, String packageIndex) throws FileNotFoundException {
        StringBuilder content = new StringBuilder("package " + packageIndex + ";");
        try (Scanner scanner = new Scanner(fileName)) {
            while (scanner.hasNext()) {
                content.append("\r\n" + scanner.nextLine());
            }
        }
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.print(content.toString());
        }
    }
}
