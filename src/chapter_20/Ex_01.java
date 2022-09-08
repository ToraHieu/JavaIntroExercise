package chapter_20;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Ex_01 {
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
        Collections.sort(words);
        System.out.println(words);
    }
}
