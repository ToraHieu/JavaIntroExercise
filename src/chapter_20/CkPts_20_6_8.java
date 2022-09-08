package chapter_20;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

public class CkPts_20_6_8 {
    public static void main(String[] args) {
        double[][] x = {{3, 1}, {2, -1}, {2, 0}, {1, 1}};
        Arrays.sort(x, 0, x.length, Comparator.comparing((Function<double[], Double>) e -> e[1]).thenComparing(e -> e[0]));
        System.out.println(Arrays.deepToString(x));
    }

}
