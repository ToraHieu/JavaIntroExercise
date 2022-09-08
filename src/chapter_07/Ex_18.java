package chapter_07;

public class Ex_18 {
	public static void main(String[] agrs) {
		int[] array = {1, 4, 8, 9, 7, 6, 7, 3, 2};
		bubbleSort(array);
		for (int u: array) {
			System.out.print(u + " ");
		}
	}
	public static void bubbleSort(int[] array) {
		for(int i = 0; i < array.length-1; i++) {
			for (int j = i+1; j <array.length; j++) {
				if (array[i] > array[j]) {
					int temp = array[i];
					array[i] = array[j]; 
					array[j] = temp;
				}
			}
		}
		return;
	}

}
