package chapter_08;
import java.util.*;
public class Ex_29 {
	private static Scanner input = new Scanner(System.in);
	public static void main(String[] agrs) {
		final int ROW = 3, COLUMN = 3;
		int[][] array1 = new int[ROW][COLUMN];
		int[][] array2 = new int[ROW][COLUMN];
		
		System.out.print("Enter list1: "); 
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				array1[i][j] = input.nextInt();
			}
		}
		
		System.out.print("Enter list2: "); 
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				array2[i][j] = input.nextInt();
			}
		}
		
		if (identical(array1, array2)) 
			System.out.print("The two arrays are identical");
		else 
			System.out.print("The two arrays are not identical");
		
	}
	
	public static boolean identical(int[][] list1, int[][] list2) {
		// Making copies of source arrays.
		int[][] list1Copy = new int[list1.length][list1[0].length];
		System.arraycopy(list1, 0, list1Copy, 0, list1.length);
		int[][] list2Copy = new int[list2.length][list2[0].length];
		System.arraycopy(list2, 0, list2Copy, 0, list2.length);
		
		// Sorting rows
		for (int i = 0; i < list1Copy.length; i++) {
			Arrays.sort(list1Copy[i]);
			Arrays.sort(list2Copy[i]);
		}
		for (int i = 0; i < list1Copy.length; i++) {
			for (int j = 0; j < list1Copy[0].length; j++) {
				if (list1Copy[i][j] != list2Copy[i][j]) 
					return false;
			}
		}
		return true;
	}
	
}
