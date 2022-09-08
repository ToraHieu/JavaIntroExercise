package chapter_07;
import java.util.Scanner;
public class Ex_35 {
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		// Hangman game!
		
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
			System.out.println("The word is " + word+ ". You missed " + missCount + " times.");
			System.out.print("Do you want to guess another word? Enter y or n> ");
			String answer = input.next();
			gameContinue = (answer.charAt(0) == 'y') ? true : false;
		}
		System.out.print("Game over.");
	}
	
	public static String randomWordGenerate() {
		String[] words = {"intelligent", "gaming", "program", "xxx", "pc", "master", "race"};
		return words[(int)(Math.random()*words.length)];
	}
	
	public static boolean gameDone(boolean[] charGuessed) {
		for (int i = 0; i < charGuessed.length; i++) {
			if (!charGuessed[i])
				return false;
		}
		return true;
	}
	
}