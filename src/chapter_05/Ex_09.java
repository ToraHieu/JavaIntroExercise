package chapter_05;

import java.util.*;

public class Ex_09 {
	private static Scanner in;
	
	public static void main (String[] agrs) {
		in = new Scanner (System.in);
		
		System.out.print("Enter the number of student: ");
		int num = in.nextInt();
		String cancel = in.nextLine();
		
		String Highest = null, SecondHighest = null;
		int HighestScore = 0, SecondHighestScore = 0;
		
		for (int i = 1; i <= num; i++) {
			System.out.println("Enter student number " + i + "  name: ");
			String currentName = in.nextLine();
			System.out.println("Enter student number " + i + "  score: ");
			int currentScore = in.nextInt();
			
			if (currentScore > HighestScore) {
				SecondHighestScore = HighestScore;
				SecondHighest = Highest;
				HighestScore = currentScore;
				Highest = currentName;
				cancel = in.nextLine();
				
			}
			else if (currentScore > SecondHighestScore) {
				SecondHighestScore = currentScore;
				SecondHighest = currentName;
			}

		}
		
		System.out.print("The student with highest score and second highest socre are: " + Highest + " and " + SecondHighest + cancel);
	}

}
