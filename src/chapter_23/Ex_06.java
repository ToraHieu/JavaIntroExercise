package chapter_23;

import java.util.Arrays;
import java.util.Comparator;

public class Ex_06 {
    public static void main(String[] args) {
        int[] list = {1, 2, 3, 4, 6, 7};
        System.out.println(ordered(list));

        double[] list1 = {1.0, 2.1, 2.1, 7, 4, 6, 7};
        System.out.println(ordered(list1));

        Integer[] list2 = {7, 6, 2, 2, 1, 0};
        System.out.println(ordered(list2, false));
    }

    public static boolean ordered(int[] list) {
        return ordered(list, true);
    }

    public static boolean ordered(int[] list, boolean ascending) {
        Integer[] wrappedInts = Arrays.stream(list)
                .boxed()
                .toArray(Integer[]::new);

        return ordered(wrappedInts, Comparator.naturalOrder(), ascending);
    }

    public static boolean ordered(double[] list) {

        return ordered(list, true);
    }

    public static boolean ordered(double[] list, boolean ascending) {
        Double[] wrappedDoubles = Arrays.stream(list)
                .boxed()
                .toArray(Double[]::new);

        return ordered(wrappedDoubles, Comparator.naturalOrder(), ascending);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list) {
        return ordered(list, true);
    }

    public static <E extends Comparable<E>> boolean ordered(E[] list, boolean ascending) {
        return ordered(list, Comparator.naturalOrder(), ascending);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator) {
        return ordered(list, comparator, true);
    }

    public static <E> boolean ordered(E[] list, Comparator<? super E> comparator, boolean ascending) {
        if (ascending) {
            for (int i = 0; i < list.length - 1; )
                if (comparator.compare(list[i], list[++i]) > 0)
                    return false;
        } else {
            for (int i = 0; i < list.length - 1; )
                if (comparator.compare(list[i], list[++i]) < 0)
                    return false;
        }

        return true;
    }
}
