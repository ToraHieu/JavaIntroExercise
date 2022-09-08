package chapter_08;

public class Ex_04 {
	public static void main(String[] agrs) {
		int[][] workHours = {
				{2, 4, 3, 4, 5, 8, 8}, 
				{7, 3, 4, 3, 3, 4, 4},
				{3, 3, 4, 3, 3, 2, 2},
				{9, 3, 4, 7, 3, 4, 1}, 
				{3, 5, 4, 3, 6, 3, 8}
		};
		
		int[][] totalHours = new int[workHours.length][2];
		getName(totalHours, 0);
		computeSum(workHours, totalHours, 1);
		bubbleSort(totalHours, 1);
		for (int i = 0; i < totalHours.length; i++) {
			System.out.println("Employee " + totalHours[i][0] + " had worked " + totalHours[i][1] + " hours.");
		}
	}
	
	public static void getName(int[][] array, int nameIndex) {
		for (int i = 0; i < array.length; i++) 
			array[i][nameIndex] = i;
	}
	
	public static void computeSum(int[][] source, int[][] result, int resultIndex) {
		for (int i = 0; i < source.length; i++) {
			int sum = 0;
			for (int j = 0; j < source[i].length; j++) 
				sum+= source[i][j];
			result[i][resultIndex] = sum;
		}
	}
	
	public static void bubbleSort(int[][] array, int sortIndex) {
		// Bubble sort the array in ascending order.
		for (int i = 0; i < array.length-1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i][sortIndex] > array[j][sortIndex]) {
					int tempA = array[i][0], 
						tempB = array[i][sortIndex];
					array[i][0] = array[j][0];
					array[i][sortIndex] = array[j][sortIndex];
					array[j][0] = tempA;
					array[j][sortIndex] = tempB;
				}
			}
		}
	}

}
