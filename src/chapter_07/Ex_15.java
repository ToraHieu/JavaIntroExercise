package chapter_07;

public class Ex_15 {
	public static void main(String[] agrs) {
		int[] numbers = {1, 2, 3, 2, 1, 6, 3, 4, 5, 2};
		int[] eliminated = eliminateDuplicates(numbers);
		for (int u: eliminated) {
			System.out.print(u + " ");
		}
	}
	
	public static int[] eliminateDuplicates(int[] list) {
		int numberOfDuplicates = countDuplicates(list);
		int[] result = new int[list.length - numberOfDuplicates]; 
		result[0] = list[0];
		int count = 0;
		for (int i = 1; i < list.length; i++) {
			if (!isDuplicate(result, list[i])) {
				count++;
				result[count] = list[i];
			}
			
		}
		return result;
	}
	
	public static int linearSearch(int[] list, int key) {
		for (int i = 0; i< list.length; i++) {
			if (list[i] == key) {
				return i;
			}
		}
		return -1;
	}
	
	public static boolean isDuplicate(int[] array, int number) {
		for (int i = 0; i < array.length; i++) {
			if (number == array[i])
				return true;
		}
		return false;
	}
	
	public static int countDuplicates(int[] list) {
		boolean[] duplicates = new boolean[list.length];
		for (int i = 1; i < list.length; i++) {
			for (int j = 0; j < i; j++) {
				if (list[i] == list[j])
					duplicates[i] = true;
			}
		}
		
		int count = 0;
		for (int i = 0; i < duplicates.length; i++) {
			if (duplicates[i])
				count++;
		}
		return count;
	}
}
