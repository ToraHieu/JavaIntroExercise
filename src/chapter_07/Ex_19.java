package chapter_07;

import java.util.Scanner;

public class Ex_19 {
	private static Scanner in = new Scanner(System.in);
	public static void main(String[] agrs) {
		System.out.println("Enter a list of numbers, note that the first number indicates the number of the elements in the list.");
		int n = in.nextInt();
		int[] list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = in.nextInt();
		}
		if(isSorted(list)) 
			System.out.println("The list is already sorted");
		else 
			System.out.println("The list is not sorted");

	}
	public static boolean isSorted(int[] list) {
		int[] testList = new int[list.length];
		System.arraycopy(list,0,testList,0,list.length);
		java.util.Arrays.sort(list); 
		if (java.util.Arrays.equals(list, testList))
			return true;
		return false;
	}

}
