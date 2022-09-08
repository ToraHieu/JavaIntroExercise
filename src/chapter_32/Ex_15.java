package chapter_32;

import java.math.BigDecimal;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Ex_15 {
    public static void main(String[] args) {
        // Create a list
        final int N = 500_000_000;
        double[] list = new double[N];
        for (int i = 0; i < list.length;)
            list[i] = ++i;

        long startTime = System.currentTimeMillis();
        System.out.println("The Sum number is " + parallelSum(list));
        long endTime = System.currentTimeMillis();
        System.out.println("Number of processors is " +
                Runtime.getRuntime().availableProcessors());
        System.out.println("Time with " + (endTime - startTime)
                + " milliseconds");

        startTime = System.currentTimeMillis();
        double seqSum = sum(list, 0, list.length);
        endTime = System.currentTimeMillis();
        System.out.println("\nThe Sum number is " + seqSum);
        System.out.println("\nSequential time is " +
                (endTime - startTime) + " milliseconds");
    }

    public static double parallelSum(double[] list) {
        RecursiveTask<BigDecimal> task = new SumTask(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(task).doubleValue();
    }

    private static class SumTask extends RecursiveTask<BigDecimal> {
        private static final int THRESHOLD = 100_000;
        double[] list;
        int start, stop;

        public SumTask(double[] list, int start, int stop) {
            this.list = list;
            this.start = start;
            this.stop = stop;
        }

        @Override
        protected BigDecimal compute() {
            if (stop - start <= THRESHOLD) {
                return BigDecimal.valueOf(sum(list, start, stop));
            } else {
                int midIndex = start + (stop - start) / 2;
                RecursiveTask<BigDecimal> left = new SumTask(list, start, midIndex);
                RecursiveTask<BigDecimal> right = new SumTask(list, midIndex, stop);
                left.fork();
                right.fork();

                return left.join().add(right.join());
            }
        }
    }

    /** Returns the sum of the elements in the list from start (inclusive) to stop (exclusive) */
    public static double sum(double[] list, int start, int stop) {
        double result = 0;
        for (int i = start; i < stop; i++) {
            result += list[i];
        }
        return result;
    }
}
