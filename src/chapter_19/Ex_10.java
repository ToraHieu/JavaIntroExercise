package chapter_19;

import java.util.ArrayList;
import java.util.Arrays;

public class Ex_10 {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(5, 2, 5, 6, -1, 10, 4, 9, 2));
        System.out.print(min(list));
    }
    public static <E extends Comparable<E>> E min(ArrayList<E> list) {
        if (list.isEmpty())
            return null;

        E currentMin = list.get(0);

        for (int i = 1; i < list.size() - 1; i++) {
            // Find the minimum in the list[i+1 ... list.size() - 2]
            if (currentMin.compareTo(list.get(i)) > 0)
                currentMin = list.get(i);
        }
        return currentMin;
    }
}
