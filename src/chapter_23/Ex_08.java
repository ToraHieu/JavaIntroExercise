package chapter_23;

import java.util.Comparator;

public class Ex_08 {
    public static <E extends Comparable<E>> void insertionSort(E[] list) {
        insertionSort(list, Comparator.naturalOrder());
    }

    public static <E> void insertionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 1; i < list.length; i++) {
            // Insert list[i] into a sorted sublist list[0..i-1] so that list[0..i] is sorted.
            E currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && comparator.compare(list[k], currentElement) > 0; k--) {
                list[k + 1] = list[k];
            }

            // Insert the current element into list[k+1]
            list[k + 1] = currentElement;
        }
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        insertionSort(list);

        String[] list2 = {"abc", "acb", "aaaa", "Abc", "s", "xyz"};
        insertionSort(list2, String::compareToIgnoreCase);
        for (int value : list) System.out.print(value + " ");
        for (String value : list2) System.out.print(value + " ");
    }
}
