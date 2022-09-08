package chapter_22;

/** (Fill and sort array)
 * Write a program that randomly fills an array of integers
 * and then sorts it. The array size is entered by the user
 * and the values are chosen at random in { - 1, 0, 1}.
 * Analyze the time complexity of your program.*/
/* This is the one variant of the Dutch national flag problem */
public class Ex_05 {
    public static void main(String[] args) {
        System.out.print("Enter the size: ");
        int size = new java.util.Scanner(System.in).nextInt();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random() * 3) - 1;
        }
        for (int i: arr)
            System.out.print(i + " ");
        sort(arr);
        System.out.println();
        for (int i: arr)
            System.out.print(i + " ");
    }

    // For more detail about this algorithm, research the said problem above.
    // The time complexity is O(n)
    private static void sort(int[] arr) {
        // i is the boundary for the numbers less than the mid value (0 in this case)
        // j is the position of the current number
        // n is the boundary of numbers greater than mid value.
        int i = 0, j = 0, n = arr.length - 1;
        while (j <= n) {
            if (arr[j] < 0) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] =temp;
                i++;
                j++;
            } else if (arr[j] > 0) {
                int temp = arr[j];
                arr[j] = arr[n];
                arr[n] =temp;
                n--;
            } else
                j++;
        }
    }
}
