package chapter_30;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex_17 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("SourceFile.txt"));
        Set<Character> vowels = new HashSet<>();
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

        StringBuilder stringBuilder = new StringBuilder();

        while (input.hasNextLine()) {
            stringBuilder.append(input.nextLine());
        }
        String content = stringBuilder.toString();
        int letterCount = (int) content.toUpperCase().chars().filter(Character::isLetter).count();
        int vowelsCount = (int) content.toUpperCase().chars().filter(i -> vowels.contains((char) i)).count();

        System.out.println("Vowels: " + vowelsCount);
        System.out.println("Consonants: " + (letterCount - vowelsCount));
    }

}
