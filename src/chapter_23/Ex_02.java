package chapter_23;

import java.util.Comparator;

public class Ex_02 {
    public static <E extends Comparable<E>> void mergeSort(E[] list) {
        mergeSort(list, (Comparable::compareTo));
    }

    /** The method for sorting the numbers */
    @SuppressWarnings("unchecked")
    public static <E> void mergeSort(E[] list, Comparator<E> comparator) {
        if (list.length > 1) {
            // Merge sort the first half
            E[] firstHalf = (E[]) new Object[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf, comparator);

            // Merge sort the second half
            int secondHalfLength = list.length - list.length / 2;
            E[] secondHalf = (E[]) new Object[secondHalfLength];
            System.arraycopy(list, list.length / 2,
                    secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf, comparator);

            // Merge firstHalf with secondHalf into list
            merge(firstHalf, secondHalf, list, comparator);
        }
    }

    /** Merge two sorted lists */
    public static <E> void merge(E[] list1, E[] list2, E[] temp, Comparator<E> comparator) {
        int current1 = 0; // Current index in list1
        int current2 = 0; // Current index in list2
        int current3 = 0; // Current index in temp

        while (current1 < list1.length && current2 < list2.length) {
            if (comparator.compare(list1[current1],list2[current2]) < 0)
                temp[current3++] = list1[current1++];
            else
                temp[current3++] = list2[current2++];
        }

        while (current1 < list1.length)
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)
            temp[current3++] = list2[current2++];
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        mergeSort(list);

        String[] list2 = {"abc", "acb", "aaaa", "Abc", "s", "xyz"};
        mergeSort(list2, String::compareToIgnoreCase);
        for (int value : list) System.out.print(value + " ");
        for (String value : list2) System.out.print(value + " ");
    }
}
