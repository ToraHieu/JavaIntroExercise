package chapter_18;

import java.util.Arrays;

public class Ex_06 {
    /** Fills the array x between l and r with random values between a and b such that x is sorted.
     *
     * @param x the array
     * @param l length of the array
     * @param r current filling index
     * @param a left value of random input
     * @param b right value of random input
     */
    public static void randomFillSortedArray(int[] x, int l, int r, int a, int b) {
        if (l <= r) {
            Arrays.sort(x);
            return;
        }
        int number = (int)(Math.random() * (b - a + 1));
        x[r] = number;
        randomFillSortedArray(x, l, r + 1, a, b);
    }

    public static void main(String[] args) {
        int[] x = new int[10];
        randomFillSortedArray(x, 10, 0, 0, 1000);
        System.out.print(Arrays.toString(x));
    }
}
