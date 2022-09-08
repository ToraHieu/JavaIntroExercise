package chapter_06;

public class Ex_30 {
	public static void main (String[] agrs) {
		int dice1, dice2;
		dice1 = rollDice1();
		dice2 = rollDice2();
		int sum = dice1 + dice2;
		System.out.printf("You rolled %d + %d = %d\n", dice1, dice2, sum);
		switch (sum) {
		case 1:
		case 2:
		case 12: 
			System.out.println("You lose!");
			break;
		case 7:
		case 11: 
			System.out.println("You win!");
			break;
			default: {
				int point = sum;
				System.out.println("Point established!");
				do {
					System.out.println("Game continue: ");
					dice1 = rollDice1();
					dice2 = rollDice2();
					sum = dice1 + dice2;
					System.out.printf("You rolled %d + %d = %d\n", dice1, dice2, sum);
				} while (gameContinue(point, sum));
				
				if (point == sum)
					System.out.println("Point has been met. You win!");
				else 
					System.out.println("You lose!");
			}
		}
	}
	
	public static int rollDice1() {
		return (int) (1 + Math.random()*6);
	}
	
	public static int rollDice2() {
		return (int) (1 + Math.random()*6);
	}
	
	public static boolean gameContinue(int point, int sum) {
		if (sum == 7 || sum == point)
			return false;
		return true;
	}

}
