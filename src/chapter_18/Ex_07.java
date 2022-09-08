package chapter_18;

import java.util.Scanner;

public class Ex_07 {
    private static int fibCount = 0;
    /** Main method */
    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for a Fibonacci number: ");
        int index = input.nextInt();

        // Find and display the Fibonacci number
        System.out.println("The Fibonacci number at index " + index + " is " + fib(index));
        System.out.print(fibCount);
    }

    /** The method for finding the Fibonacci number */
    public static long fib(long index) {
        fibCount++;
//        // Non-tail recursion
//        if (index == 0) // Base case
//            return 0;
//        else if (index == 1) // Base case
//            return 1;
//        else // Reduction and recursive calls
//            return fib(index - 1)  + fib(index - 2);
        return fib(index, 0, 1);
    }

    /** Tail recursion */
    private static long fib(long index, long value1, long value2) {
        fibCount++;
        if (index == 0)
            return value1;
        if (index == 1)
            return value2;
        return fib(index - 1, value2, value1 + value2);
    }
}
