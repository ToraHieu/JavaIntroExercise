package chapter_30;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Ex_08 {
    private static final int LIMIT = 10;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter " + LIMIT + " numbers: ");
            int[] list = new int[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                list[i] = in.nextInt();
            }
            System.out.print("The distinct numbers are:" );
            for (int i: eliminateDuplicates(list)) {
                System.out.print(" " + i);
            }
        }
    }

    public static int[] eliminateDuplicates(int[] list) {
        return IntStream.of(list).distinct().toArray();
    }
}
