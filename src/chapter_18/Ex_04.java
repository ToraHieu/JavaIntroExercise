package chapter_18;

public class Ex_04 {
    /** Return the combination of n things taken p at a time without repetition.*/
    public static long C(long n, long p) {
        // Check on special case (p > n)
        // or incorrect input: p < 0 || n < 0 but we don't need to check n < 0 because p <= n and p >= 0
        // after the first two conditions
        if (p > n || p < 0)
            return 0;
        return CHelper(n, p);
    }

    public static void main(String[] args) {
        System.out.print(C(52, 5));
    }

    private static long CHelper(long n, long p) {
        if (n == p || p == 0) // Base case
            return 1;
        return CHelper(n - 1, p) + CHelper(n - 1, p-1);
    }
}
