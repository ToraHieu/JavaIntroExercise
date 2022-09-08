package chapter_07;

import java.util.Scanner;

public class Ex_17 {
	private static Scanner in = new Scanner(System.in);
	private static Scanner inLine = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		System.out.print("Enter the number of students: ");
		int n = in.nextInt();
		String[] names = new String[n];
		double[] scores = new double[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Enter the student number " + i + " name: ");
			names[i] = inLine.nextLine();
			System.out.print("Enter the student number " + i + " score: ");
			scores[i] = in.nextDouble();
		}
		
		// Selection sort: Descending
		for (int i = 0; i < n-1; i++) {
			double currentMax = scores[i];
			int currentMaxIndex = i;
			for (int j = i+1; j < n; j++) {
				if (currentMax < scores[j]) {
					currentMax = scores[j];
					currentMaxIndex = j;
				}
			}
			if (currentMaxIndex != i) {
				scores[currentMaxIndex] = scores[i];
				scores[i] = currentMax;
				String temp = names[i];
				names[i] = names[currentMaxIndex];
				names[currentMaxIndex] = temp;
			}
		}
		
		for (String u: names) {
			System.out.println(u);
		}
	}
}
