package chapter_07;
//Didn't do this exercise. Couldn't figure out the idea.
public class Ex_32 {
	private static java.util.Scanner in = new java.util.Scanner(System.in);
	
	public static void main(String[] agrs) {
		System.out.print("Enter list: ");
		int n = in.nextInt();
		int[] list = new int[n];
		for (int i = 0; i < n; i++) {
			list[i] = in.nextInt();
		}
		
		int index = partition(list);
		System.out.print("After the partition, the index is " + index + ", the list is");
		for (int u: list)
			System.out.print(" " + u);
	}
	
	// Partition the array list[first..last] 
	  public static int partition(int[] list) {
	  	int first = 0;
	  	int last = list.length - 1;
	    int pivot = list[first]; // Choose the first element as the pivot
	    int low = first + 1; // Index for forward search
	    int high = last; // Index for backward search

	    while (high > low) {
	      // Search forward from left
	      while (low <= high && list[low] <= pivot)
	        low++;

	      // Search backward from right
	      while (list[high] > pivot)
	        high--;

	      // Swap two elements in the list
	      if (high > low) {
	        int temp = list[high];
	        list[high] = list[low];
	        list[low] = temp;
	      }
	    }
	    
	    // To search for position of the pivot.
	    while (high > first && list[high] >= pivot)
	      high--;

	    // Swap pivot with list[high]
	    if (pivot > list[high]) {
	      list[first] = list[high];
	      list[high] = pivot;
	      return high;
	    }
	    else {
	      return first;
	    }
	  }
}
