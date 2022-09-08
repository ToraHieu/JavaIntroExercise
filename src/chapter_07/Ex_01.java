package chapter_07;

import java.util.Scanner;

public class Ex_01 {
	private static Scanner in = new Scanner(System.in);
	
	public static void main (String[] args) {
		System.out.print("Enter the number of students: ");
		int stuNum = in.nextInt();
		int[] stuScore = new int[stuNum];
		System.out.print("Enter " + stuNum + " scores: ");
		for (int i = 0; i < stuNum; i++) {
			stuScore[i] = in.nextInt();
		}
		int maxGrade = arrayMax(stuScore);
		char[] result = grading(stuScore, maxGrade);
		for (int i = 0; i < stuNum; i++) 
			System.out.printf("Student %d score is %d and grade is %c\n",i,stuScore[i],result[i]);
		
	}
	
	public static int arrayMax(int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (max < array[i]) 
				max = array[i];
		}
		return max;
	}
	
	public static char[] grading (int[] array, int max) {
		char[] result = new char[array.length];
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= max - 10)
				result[i] = 'A';
			else if (array[i] >= max - 20)
				result[i] = 'B';
			else if (array[i] >= max - 30)
				result[i] = 'C';
			else if (array[i] >= max - 40)
				result[i] = 'D';
			else
				result[i] = 'F';
		}
		return result;
	}
}
