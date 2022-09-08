package chapter_32;

import chapter_23.MergeSort;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Ex_13 {
    public static void main(String[] args) {
        final int SIZE = 7_000_000;
        Integer[] list1 = new Integer[SIZE];
        int[] list2 = new int[SIZE];

        for (int i = 0; i < list1.length; i++)
            list1[i] = list2[i] = (int)(Math.random() * 10000000);

        long startTime = System.currentTimeMillis();
        parallelMergeSort(list1); // Invoke parallel merge sort
        long endTime = System.currentTimeMillis();
        System.out.println("\nParallel time with "
                + Runtime.getRuntime().availableProcessors() +
                " processors is " + (endTime - startTime) + " milliseconds");

        startTime = System.currentTimeMillis();
        MergeSort.mergeSort(list2); // MergeSort is in Listing 24.5
        endTime = System.currentTimeMillis();
        System.out.println("\nSequential time is " +
                (endTime - startTime) + " milliseconds");
    }

    public static <E extends Comparable<E>> void parallelMergeSort(E[] list) {
        RecursiveAction mainTask = new SortTask<>(list);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(mainTask);
    }

    private static class SortTask <E extends Comparable<E>> extends RecursiveAction {
        private static final int THRESHOLD = 500;
        private final E[] list;

        SortTask(E[] list) {
            this.list = list;
        }

        @Override
        @SuppressWarnings({"unchecked"})
        protected void compute() {
            if (list.length < THRESHOLD)
                java.util.Arrays.sort(list);
            else {
                // Obtain the first half
                int mid = list.length / 2;
                E[] firstHalf = (E[]) new Comparable[mid];;
                System.arraycopy(list, 0, firstHalf, 0, mid);

                // Obtain the second half
                int secondHalfLength = list.length - mid;
                E[] secondHalf = (E[]) new Comparable[secondHalfLength];
                System.arraycopy(list, mid,
                        secondHalf, 0, secondHalfLength);

                // Recursively sort the two halves
                invokeAll(new SortTask<>(firstHalf),
                        new SortTask<>(secondHalf));

                // Merge firstHalf with secondHalf into list
                int current1 = 0; // Current index in list1
                int current2 = 0; // Current index in list2
                int current3 = 0; // Current index in temp

                while (current1 < firstHalf.length && current2 < secondHalf.length) {
                    if (firstHalf[current1].compareTo(secondHalf[current2]) < 0)
                        list[current3++] = firstHalf[current1++];
                    else
                        list[current3++] = secondHalf[current2++];
                }

                while (current1 < firstHalf.length)
                    list[current3++] = firstHalf[current1++];

                while (current2 < secondHalf.length)
                    list[current3++] = secondHalf[current2++];
            }
        }
    }
}
