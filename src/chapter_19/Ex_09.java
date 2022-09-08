package chapter_19;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_09 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 5, 6, -1, -10, 4, -10, 2));
        sort(list);
        System.out.print(list);
    }

    /** Sort the ArrayList using Selection sort algorithm */
    public static <E extends Comparable<E>> void sort(ArrayList<E> list) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.size() - 1; i++) {
            // Find the minimum in the list[i+1 ... list.size() - 2]
            currentMin = list.get(i);
            currentMinIndex = i;

            for (int j = i + 1; j < list.size(); j++) {
                if (currentMin.compareTo(list.get(j)) > 0) {
                    currentMin = list.get(j);
                    currentMinIndex = j;
                }
            }

            // Swap list[i] with list[currentMinIndex if necessary
            list.set(i, list.set(currentMinIndex, list.get(i)));
        }
    }
}
