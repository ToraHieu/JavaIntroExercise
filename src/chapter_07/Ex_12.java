package chapter_07;

public class Ex_12 {
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7};
		reverse(array);
		for (int u: array) {
			System.out.print(u + " ");
		}
	}
	
	public static int[] reverse(int[] array) {
		for (int i = 0; i < array.length/2; i++) {
			swap(i, array.length-1-i, array);
		}
		return array;
	}
	public static void swap(int x, int y, int[] array) {
		int mediate = array[x];
		array[x] = array[y];
		array[y] = mediate;
	}
}
