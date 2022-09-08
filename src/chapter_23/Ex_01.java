package chapter_23;

import java.util.Comparator;

public class Ex_01 {
    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;

            for (int i = 0; i < list.length - k; i++) {
                if (list[i].compareTo(list[i + 1]) > 0) {
                    swap(list, i, i + 1);
                    needNextPass = true; // Next pass still needed
                }
            }
        }
    }

    public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            // Array may be sorted and next pass not needed
            needNextPass = false;

            for (int i = 0; i < list.length - k; i++) {
                if (comparator.compare(list[i], list[i +1]) > 0) {
                    swap(list, i, i + 1);
                    needNextPass = true; // Next pass still needed
                }
            }
        }
    }

    private static <E> void swap(E[] list, int i, int j) {
        E temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] list = {3, -5, 5, 9, 20, -99, 69, 3, 5, 1};
        bubbleSort(list);
        for (int i : list) System.out.print(i + " ");

        System.out.println();

        Integer[] list2 = {3, -5, 5, 9, 20, -99, 69, 3, 5, 1};
        bubbleSort(list2, (i1, i2) -> (int) (i1.doubleValue() - i2.doubleValue()));
        for (int i : list2) System.out.print(i + " ");
    }
}
