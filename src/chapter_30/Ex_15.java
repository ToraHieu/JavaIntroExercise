package chapter_30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ex_15 {
    public static void main(String[] args) throws FileNotFoundException {
        // Stimulate command-line input
        args = new String[]{"SourceFile.txt"};

        ArrayList<String> words = new ArrayList<>();
        try (Scanner input = new Scanner(new File(args[0]))) {
            String temp;
            while (input.hasNext()) {
                temp = input.next();
                if (Character.isLetter(temp.charAt(0))) {
                    words.add(temp);
                }
            }
        }
        words.stream().sorted().forEach(e -> System.out.print(e + " "));
    }
}
