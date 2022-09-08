package chapter_30;

import java.util.Scanner;
import java.util.stream.IntStream;

public class Ex_01 {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter the number of students: ");
            int n = input.nextInt();
            int[] scores = new int[n];
            System.out.print("Enter " + n + " scores: ");
            for (int i = 0; i < n; i++) {
                scores[i] = input.nextInt();
            }

            //noinspection OptionalGetWithoutIsPresent
            final int max = IntStream.of(scores).max().getAsInt();
            int[] grades = IntStream.of(scores)
                    .map(e -> ('A' + (max - (max - e > 40 ? 40 : e) - 1)/10))
                    .toArray();
            for (int i = 0; i < n; i++) {
                System.out.println("Student " + i + " score is " + scores[i] + " and grade is " + (char)grades[i]);
            }
        }
    }
}
