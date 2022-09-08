package chapter_18;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ex_28 {
    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    public static long getSize(File file) {
        if (file.exists() && file.isFile())
            return file.length();

        File[] files = file.listFiles();
        List<File> list;
        if (files != null)
            list = new ArrayList<>(Arrays.asList(files));
        else
            return 0;

        long size = 0; // Store the total size of all file
        while (!list.isEmpty()) {
            File currentFile = list.remove(0);
            if (currentFile.isDirectory()) {
                File[] subFiles = currentFile.listFiles();
                if (subFiles != null)
                    list.addAll(Arrays.asList(subFiles));
            } else
                size += currentFile.length();
        }

        return size;
    }
}
