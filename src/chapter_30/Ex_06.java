package chapter_30;

import java.util.Scanner;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Ex_06 {
    private static final int LIMIT = 10;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter " + LIMIT + " integers: ");
            int[] ints = new int[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                ints[i] = in.nextInt();
            }
            System.out.println("The average int is: " + average(ints));

            System.out.print("Enter " + LIMIT + " doubles: ");
            double[] doubles = new double[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                doubles[i] = in.nextDouble();
            }
            System.out.println("The average double is: " + average(doubles));
        }
    }

    public static double average(int[] array) {
        return IntStream.of(array).average().getAsDouble();
    }

    public static double average(double[] array) {
        return DoubleStream.of(array).average().getAsDouble();
    }
}
