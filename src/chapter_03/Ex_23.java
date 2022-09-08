package chapter_03;

public class Ex_23 {
	
	public static void main (String[] agrs) {

		int SuitRandom = (int) (Math.random()*4);
		String Suit = null;
		switch (SuitRandom) {
		case (0): Suit = "Clubs"; break;
		case (1): Suit = "Diamonds"; break;
		case (2): Suit = "Hearts"; break;
		case (3): Suit = "Spades"; 
		}
		
		int RankRandom = (int) (Math.random()*13);
		String Rank = null;
		switch(RankRandom) {
		case (0): Rank = "Ace"; break;
		case (1): Rank = "2"; break;
		case (2): Rank = "3"; break;
		case (3): Rank = "4"; break;
		case (4): Rank = "5"; break;
		case (5): Rank = "6"; break;
		case (6): Rank = "7"; break;
		case (7): Rank = "8"; break;
		case (8): Rank = "9"; break;
		case (9): Rank = "10"; break;
		case (10): Rank = "Jack"; break;
		case (11): Rank = "Queen"; break;
		case (12): Rank = "King";
		}
		
		System.out.printf("The card you picked is %s of %s", Rank, Suit);
	}

}
