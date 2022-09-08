package chapter_30;

import java.util.OptionalDouble;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Ex_07 {
    private static final int LIMIT = 10;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter " + LIMIT + " numbers: ");
            double[] doubles = new double[LIMIT];
            for (int i = 0; i < LIMIT; i++) {
                doubles[i] = in.nextDouble();
            }
            System.out.print("The min is: " + min(doubles));
        }
    }
    
    public static double min(double[] array) {
        OptionalDouble optionalDouble = DoubleStream.of(array).min();
        if (optionalDouble.isPresent())
            return optionalDouble.getAsDouble();
        return Double.POSITIVE_INFINITY;
    }
}
