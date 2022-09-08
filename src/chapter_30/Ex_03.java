package chapter_30;

import java.util.DoubleSummaryStatistics;
import java.util.Scanner;
import java.util.stream.DoubleStream;

public class Ex_03 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter the scores (a negative number signifies end of input): ");
            double d; int i = 0;
            double[] array = new double[100];
            while ((d = in.nextInt()) >= 0) {
                array[i++] = d;
            }

            DoubleSummaryStatistics stats = DoubleStream.of(array).limit(i).summaryStatistics();
            double avg = stats.getAverage();
            int aboveAvgNumber = (int) DoubleStream.of(array).limit(i).filter(e -> e >= avg).count();
            System.out.println("The number of scores above or equal average: " + aboveAvgNumber + "/" + stats.getCount());
        }
    }
}
