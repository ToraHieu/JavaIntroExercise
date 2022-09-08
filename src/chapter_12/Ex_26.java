package chapter_12;

import java.io.File;
import java.util.Scanner;

public class Ex_26 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a directory: ");
            File directory = new File(input.next());
            if (directory.isDirectory()) {
                System.out.println("Directory already exists.");
            } else {
                directory.mkdirs();
                System.out.println("Directory sucessully created");
            }
        }
    }
}
