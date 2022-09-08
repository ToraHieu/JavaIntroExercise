package chapter_07;

import java.util.Scanner;

public class Ex_27 {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		System.out.print("Enter list 1: ");
		int n1 = in.nextInt();
		int[] list1 = new int[n1];
		for (int i = 0; i < n1; i++) 
			list1[i] = in.nextInt();
		System.out.print("Enter list 2: ");
		int n2 = in.nextInt();
		int[] list2 = new int[n2];
		for (int i = 0; i < n2; i++) 
			list2[i] = in.nextInt();
		if (equals(list1, list2))
			System.out.println("Two lists are identical");
		else
			System.out.println("Two lists are not identical");

		
	}
	
	public static boolean equals(int[] list1, int[] list2) {
		if (list1.length != list2.length) 
			return false;
		int n = list1.length;
		int[] list1Copy = new int[n];
		int[] list2Copy = new int[n];
		
		System.arraycopy(list1, 0, list1Copy, 0, n);
		System.arraycopy(list2, 0, list2Copy, 0, n);
		java.util.Arrays.sort(list1Copy);
		java.util.Arrays.sort(list2Copy);
		
		for (int i = 0; i < n; i++) {
			if (list1Copy[i] != list2Copy[i])
				return false;
		}
		return true;
	}

}
