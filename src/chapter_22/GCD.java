package chapter_22;

public class GCD {
    /** Find GCD for integers m and n */
    public static long gcd(long m, long n) {
        long gcd = 1;
        if (m % n == 0)
            return n;

        for (long k = n / 2; k >= 1; k--) {
            if (m % k == 0 && n % k == 0) {
                gcd = k;
                break;
            }
        }

        return gcd;
    }
}
