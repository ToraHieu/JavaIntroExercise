package chapter_12;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/** A quick fix to complete the exercise. 
 * There should be better ways to implement it. */
public class Ex_17 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) throws Exception {
        // Hangman game now use a text file as source!

        boolean gameContinue = true;
        while (gameContinue) {
            int missCount = 0;
            String word = randomWordGenerate();
            boolean[] charGuessed = new boolean[word.length()];
            while (!gameDone(charGuessed)) {
                System.out.print("(Guess) Enter a letter in word ");
                for (int i = 0; i < charGuessed.length; i++) {
                    if (charGuessed[i])
                        System.out.print(word.charAt(i));
                    else
                        System.out.print("*");
                }
                System.out.print(" > ");
                String s = input.next();
                char guess = s.charAt(0);
                boolean guessCorrect = false;
                for (int i = 0; i < charGuessed.length; i++) {
                    if (guess == word.charAt(i) && charGuessed[i]) {
                        System.out.println("\t" + guess + " is already in the word");
                        guessCorrect = true;
                        break;
                    }
                    if (guess == word.charAt(i)) {
                        charGuessed[i] = true;
                        guessCorrect = true;
                    }
                }
                if (!guessCorrect) {
                    System.out.println("\t" + guess + " is not in the word.");
                    missCount++;
                }
            }
            System.out.println("The word is " + word + ". You missed " + missCount + " times.");
            System.out.print("Do you want to guess another word? Enter y or n> ");
            String answer = input.next();
            gameContinue = (answer.charAt(0) == 'y') ? true : false;
        }
        System.out.print("Game over.");
    }

    public static String randomWordGenerate() throws Exception {
        File source = new File("Ch12_Ex_17.txt");
        if (!source.exists()) {
            System.out.print("Error: Source file can't be found");
            System.exit(1);
        }
        Scanner input = new Scanner(source);
        ArrayList<String> words = new ArrayList<>();
        while (input.hasNext()) {
            words.add(input.next());
        }
        input.close();
        return words.get((int) (Math.random() * words.size()));
    }

    public static boolean gameDone(boolean[] charGuessed) {
        for (int i = 0; i < charGuessed.length; i++) {
            if (!charGuessed[i])
                return false;
        }
        return true;
    }
}
