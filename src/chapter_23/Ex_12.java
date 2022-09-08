package chapter_23;

import java.util.LinkedList;

/** (Radix sort)
 * Write a program that randomly generates 1,200,000 integers and sorts them using radix sort.
 * */
public class Ex_12 {
    private static final int LIST_LENGTH = 1_200_000;
    public static void main(String[] args) {
        int[] list = new int[LIST_LENGTH];
        for (int i = 0; i < LIST_LENGTH; i++) {
            list[i] = (int) (Math.random() * 100_000);
        }
        radixSort(list);
        for (int i: list) System.out.print(i + " ");
    }

    public static void radixSort(int[] list) {
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[10];
        for (int i = 0; i < buckets.length; i++) buckets[i] = new LinkedList<>();

        int radix = 10, divider = 1;
        boolean needNextPass = true;
        while (needNextPass) {
            needNextPass = false;
            for (int value : list) {
                int key = (int) ((float) (value % radix) / divider);
                buckets[key].add(value);
            }

            int k = 0;
            for (int i = 0; i < buckets.length; i++) {
                LinkedList<Integer> bucket = buckets[i];
                if (bucket.size() != 0) {
                    if (!needNextPass && i > 0) needNextPass = true; // If nextPass is already needed, no need to check again.
                    while (bucket.size() > 0) {
                        list[k++] = bucket.remove(0);
                    }
                }
            }

            divider = radix;
            radix *= 10;
        }
    }
}
