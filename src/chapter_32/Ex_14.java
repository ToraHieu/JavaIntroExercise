package chapter_32;

import chapter_23.QuickSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Ex_14 {
    public static void main(String[] args) {
        final int SIZE = 7_000_000;
        int[] list1 = new int[SIZE];
        int[] list2 = new int[SIZE];

        for (int i = 0; i < list1.length; i++)
            list1[i] = list2[i] = (int)(Math.random() * 10000000);

        long startTime = System.currentTimeMillis();
        parallelQuickSort(list1);
        long endTime = System.currentTimeMillis();
        System.out.println("\nParallel time with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        QuickSort.quickSort(list2);
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential time is " +
                (endTime - startTime) + " milliseconds");
//        for (int i: list1) {
//            System.out.println(i);
//        }
    }

    public static void parallelQuickSort(int[] list) {
        RecursiveAction mainTask = new SortAction(list, 0, list.length - 1);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortAction extends RecursiveAction {
        private static final int THRESHOLD = 100;
        int[] list;
        int first, last;

        public SortAction(int[] list, int first, int last) {
            this.list = list;
            this.first = first;
            this.last = last;
        }

        @Override
        protected void compute() {
            if (last - first <= THRESHOLD) {
                insertionSort(list, first, last);
            } else if (last > first) {
                int pivotIndex = QuickSort.partition(list, first, last);
                invokeAll(new SortAction(list, first, pivotIndex - 1),
                        new SortAction(list, pivotIndex + 1, last));
            }
        }
    }

    /** Sort in ascending order part of the array list from index start to stop (inclusive) */
    private static void insertionSort(int[] list, int start, int stop) {
        for (int i = start + 1; i <= stop; i++) {
            int key = list[i];
            int j = i - 1;
            while (j >= start && list[j] > key) {
                list[j + 1] = list[j];
                j = j - 1;
            }
            list[j + 1] = key;
        }
    }
}
