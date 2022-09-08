package chapter_20;

import java.util.Arrays;
import java.util.Comparator;

public class CkPts_20_6_7 {
    public static void main(String[] args) {
        double[][] x = {{3, 1}, {2, -1}, {2, 0}};
        Arrays.sort(x, 0, x.length, Comparator.comparing(e -> e[1]));
        System.out.println(Arrays.deepToString(x));
    }
}
