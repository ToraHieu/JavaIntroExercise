package chapter_20;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/** DirectorySize using Queue */
public class Ex_18 {
    public static void main(String[] args) {
        // Prompt the user to enter a directory or a file
        System.out.print("Enter a directory or a file: ");
        Scanner input = new Scanner(System.in);
        String directory = input.nextLine();

        // Display the size
        System.out.println(getSize(new File(directory)) + " bytes");
    }

    public static long getSize(File directory) {
//        long start = System.nanoTime();

        long size = 0;
        Queue<File> queue = new LinkedList<>();
        queue.offer(directory);

        do {
            File temp = queue.remove();
            if (temp.isDirectory()) {
                File[] sub = temp.listFiles();
                for (File file: sub)
                    queue.offer(file);
            }
            else
                size += temp.length();

        } while (!queue.isEmpty());

//        long end = System.nanoTime();
//        System.out.println(end - start);

        return size;
    }
}
