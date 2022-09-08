package chapter_18;

public class Ex_05 {
    /** Return the combination of n things taken p at a time without repetition.
     * Now re-written for better performance.*/
    public static long C(long n, long p) {
        // Check on special case (p > n)
        // or incorrect input: p < 0 || n < 0 but we don't need to check n < 0 because p <= n and p >= 0
        // after the first two conditions
        if (p > n || p < 0)
            return 0;
        // Check for special case again
        if (n == p)
            return 1;
        return CHelper(n, p, 1);
    }

    public static void main(String[] args) {
        System.out.print(C(52, 2));
    }

    /** Tail recursion for better performance.*/
    private static long CHelper(long n, long p, double result) {
        if (p == 0) // Base case
            return (long)result;
        return CHelper(n, p - 1, (result * ((n - p) + 1)) / p);
    }
}
