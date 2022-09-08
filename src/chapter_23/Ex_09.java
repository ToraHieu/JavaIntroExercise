package chapter_23;

import java.util.Comparator;

public class Ex_09 {
    public static <E extends Comparable<E>>  void heapSort(E[] list) {
        heapSort(list, Comparator.naturalOrder());
    }

    public static <E> void heapSort(E[] list, Comparator<? super E> comparator) {
        // Create a Heap of integers
        HeapWithComparator<E> heap = new HeapWithComparator<>(comparator);

        // Add elements to the heap
        for (E e : list) heap.add(e);

        // Remove elements from the heap
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    /** A test method */
    public static void main(String[] args) {
        Integer[] list = {2, 3, 2, 5, 6, 1, -2, 3, 14, 12};
        heapSort(list);

        String[] list2 = {"abc", "acb", "aaaa", "Abc", "s", "xyz"};
        heapSort(list2, String::compareToIgnoreCase);
        for (int value : list) System.out.print(value + " ");
        for (String value : list2) System.out.print(value + " ");
    }
}
