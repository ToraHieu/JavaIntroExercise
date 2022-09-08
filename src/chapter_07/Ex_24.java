package chapter_07;

public class Ex_24 {
	public static void main(String[] agrs) {
		//preparing
		int[] deck = new int[52];
		String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
		boolean[] suitsCheck = new boolean[4];
		String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", 
				"10", "Jack", "Queen", "King"};
		String[] cardsPicked = new String[4];
		
		//initialize the deck
		for (int i = 0; i < deck.length; i++) {
			deck[i] = i;
		}
		//initialize the counting array and variables
		java.util.Arrays.fill(cardsPicked, "");
		int count = 0;
		int countSuit = 0;
		
		while (!done(suitsCheck)) {
			int currentCard = pickACard();
			int currentSuit = currentCard/13;
			if (!suitsCheck[currentSuit]) {
				suitsCheck[currentSuit] = true;
				cardsPicked[countSuit] += ranks[currentCard%13] + " of " + suits[currentCard/13]; 
				countSuit++;
			}
			count++;
		}
		
		for (String u : cardsPicked) 
			System.out.println(u);
		System.out.println("Number of picks: " + count);
		
	}
	
	public static int pickACard() {
		int card = (int)(Math.random()*52);
		return card;
	}
	
	public static boolean done(boolean[] suitsCheck) {
		for (int i = 0; i < suitsCheck.length; i++) 
			if (!suitsCheck[i])
				return false;
		return true;
	}

}
