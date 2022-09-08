
package chapter_07;

import java.util.Scanner;

public class Ex_31 {
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] agrs) {
		System.out.print("Enter list1: ");
		int n1 = in.nextInt();
		int[] list1 = new int[n1];
		for (int i = 0; i < n1; i++) {
			list1[i] = in.nextInt();
		}
		System.out.print("Enter list2: ");
		int n2 = in.nextInt();
		int[] list2 = new int[n2];
		for (int i = 0; i < n2; i++) {
			list2[i] = in.nextInt();
		}
		
		int[] mergeList = merge(list1, list2);
		System.out.print("The merged list is");
		for (int u: mergeList)
			System.out.print(" " + u);
	}
	
	public static int[] merge(int[] list1, int[] list2) {
		int[] mergeList = new int[list1.length + list2.length];
		for (int i = 0; i < list1.length; i++) {
			mergeList[i] = list1[i];
		}
		for (int i = list1.length, j = 0; i < mergeList.length; i++, j++) {
			mergeList[i] = list2[j];
		}
		java.util.Arrays.sort(mergeList);
		
		return mergeList;
	}
	
	

}
