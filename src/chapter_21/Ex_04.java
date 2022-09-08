package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex_04 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("SourceFile.txt"));
        Set<Character> vowels = new HashSet<>();
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        int vowelsCount = 0, consonantsCount = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();
            for (char c: line.toCharArray()) {
                c = Character.toUpperCase(c);
                if (Character.isLetter(c)) {
                    if (vowels.contains(c))
                        vowelsCount++;
                    else
                        consonantsCount++;
                }
            }
        }

        System.out.println("Vowels: " + vowelsCount);
        System.out.println("Consonants: " + consonantsCount);
    }
}
