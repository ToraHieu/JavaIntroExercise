package chapter_27;

import chapter_24.MyArrayList;

import java.util.SplittableRandom;

public class Ex_10 {
    private static final int ORIGIN = 0;
    private static final int LENGTH = 1_000_000;
    private static final int LENGTH2 = 2_000_000;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public static void main(String[] args) {
        long startTime, endTime;

        double[] arr1 = new double[LENGTH], arr2 = new double[LENGTH];
        SplittableRandom random = new SplittableRandom();
        for (int i = 0; i < LENGTH; i++) {
            arr1[i] = random.nextDouble(ORIGIN, LENGTH);
            arr2[i] = random.nextDouble(ORIGIN, LENGTH2);
        }
        MyHashSet<Double> set = new MyHashSet<>();
        MyArrayList<Double> list = new MyArrayList<>();

        for (double e: arr1) {
            set.add(e);
            list.add(e);
        }

        startTime = System.nanoTime();
        for (double e: arr2) {
            //noinspection ResultOfMethodCallIgnored
            set.contains(e);
        }
        endTime = System.nanoTime();

        System.out.println("Set time: " + (endTime - startTime));

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) { // Terribly long time if goes a full array of 1m elements
            //noinspection ResultOfMethodCallIgnored
            list.contains(arr2[i]);
        }
        endTime = System.nanoTime();

        System.out.println("List time: " + (endTime - startTime));
    }
}
