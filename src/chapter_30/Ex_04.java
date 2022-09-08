package chapter_30;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Ex_04 {
    private static final int LIMIT = 10;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter " + LIMIT + " numbers: ");
            int[] numbers = new int[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                numbers[i] = in.nextInt();
            }
            int[] distinctNumbers = IntStream.of(numbers).distinct().toArray();
            System.out.println("The number of distinct numbers is: " + distinctNumbers.length);
            System.out.print("The distinct numbers are:");
            for (int i : distinctNumbers) {
                System.out.print(" " + i);
            }
        }
    }
}
