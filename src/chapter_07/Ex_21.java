package chapter_07;

import java.util.Scanner;

public class Ex_21 {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		System.out.print("Enter the number of balls to drop: ");
		int balls = in.nextInt();
		System.out.print("Enter the number of slots in the bean machines: ");
		int slotsNum = in.nextInt();
		int[] slots = new int[slotsNum];
		String[] ballsPath = new String[balls];
		
		for (int i = 0; i < balls; i++) {
			ballsPath[i] = "";
			int sum = 0;
			for (int j = 0; j < slotsNum-1; j++) {
				int currentDirection = generateDirection();
				sum += currentDirection;
				ballsPath[i] += getPath(currentDirection);
			}
			slots[sum]++;
		}
		
		for (String u: ballsPath) 
			System.out.println(u);
		
		for (int i = 0; i < slotsNum; i++) {
			for (int j = slotsNum - slots[i]; j > 0; j--)
				System.out.print(" ");
			for (int k = 0; k < slots[i]; k++) 
				System.out.print("O");
			System.out.println();
		}
	}
	
	public static int generateDirection() {
		int result = (int) (Math.random()*2);
		return result;
	}
	
	public static char getPath(int n) {
		if (n == 1)
			return 'R';
		else
			return 'L';
	}
	
}
