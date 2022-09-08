package chapter_18;

public class CkPts_2_4 {
    public static void main(String[] args) {
        System.out.print(powerOf2(10));
    }

    // Write a recursive mathematical definition for computing 2^n for a positive integer n.
    public static int powerOf2(int n) {
        if (n > 0)
            return 2 * powerOf2(n - 1);
        else
            return 1;
    }
}
