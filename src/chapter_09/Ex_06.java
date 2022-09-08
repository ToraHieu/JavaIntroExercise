package chapter_09;

public class Ex_06 {
	public static void main(String[] agrs) {
		int[] array = new int[100000];
		randomFill(array, 100000);
		StopWatch test = new StopWatch(); 
		test.start();
		selectionSort(array);
		test.stop();
		System.out.println("Time used: " + test.getElapsedTime());
		
	}
	
	public static void randomFill(int[] array, int range) {
		for (int i = 0; i < array.length; i++) {
			array[i] = (int) (Math.random() * range);
		}
	}
	
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length-1; i++) {
			int currentMin = array[i];
			int currentMinIndex = i;
			for (int j = i+1; j < array.length; j++) {
				if (currentMin > array[j]) {
					currentMin = array[j];
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) {
				array[currentMinIndex] = array[i];
				array[i] = currentMin;
			}
		}
	}
}
