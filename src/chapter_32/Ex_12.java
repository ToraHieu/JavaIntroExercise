package chapter_32;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

public class Ex_12 {
    public static void main(String[] args) {
        long start, stop;
        double[] list = new double[9_000_000];
        start = System.nanoTime();
        parallelAssignValues(list);
        stop = System.nanoTime();
        System.out.println("Parallel: " + (stop - start));

        Random random = new Random();
        start = System.nanoTime();
        for (int i = 0; i < list.length; i++) {
            list[i] = random.nextDouble();
        }
        stop = System.nanoTime();
        System.out.println("Sequential: " + (stop - start));
    }

    public static void parallelAssignValues(double[] list) {
        RecursiveAction mainTask = new AssignAction(list, 0, list.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class AssignAction extends RecursiveAction {
        private final static int THRESHOLD = 1000;
        private final double[] list;
        private final int low;
        private final int high;

        public AssignAction(double[] list, int low, int high) {
            this.list = list;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (high - low < THRESHOLD) {
                for (int i = low; i < high; i++) {
                    list[i] = ThreadLocalRandom.current().nextDouble();
                }
            }
            else {
                int mid = (low + high) / 2;
                RecursiveAction left = new AssignAction(list, low, mid);
                RecursiveAction right = new AssignAction(list, mid, high);

                right.fork();
                left.fork();
            }
        }
    }
}
