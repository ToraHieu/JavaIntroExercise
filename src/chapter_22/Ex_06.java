package chapter_22;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Ex_06 {
    private static final long f46 = ImprovedFibonacci.fib(46);
    private static final long f47 = ImprovedFibonacci.fib(47);
    private static final long f48 = ImprovedFibonacci.fib(48);
    private static final long f49 = ImprovedFibonacci.fib(49);
    private static final long f50 = ImprovedFibonacci.fib(50);
    private static final long f51 = ImprovedFibonacci.fib(51);


    public static void main(String[] args) {
        System.out.printf("%30s%-12d%-12d%-12d%-12d%-12d", "", 46, 47, 48, 49, 50);
        
        long start, end;

        start = System.nanoTime();
        GCD.gcd(f46, f47);
        end = System.nanoTime();
        long t11 = end - start;

        start = System.nanoTime();
        GCD.gcd(f47, f48);
        end = System.nanoTime();
        long t12 = end - start;

        start = System.nanoTime();
        GCD.gcd(f48, f49);
        end = System.nanoTime();
        long t13 = end - start;

        start = System.nanoTime();
        GCD.gcd(f49, f50);
        end = System.nanoTime();
        long t14 = end - start;

        start = System.nanoTime();
        GCD.gcd(f50, f51);
        end = System.nanoTime();
        long t15 = end - start;

        System.out.printf("\n%-30s%-12d%-12d%-12d%-12d%-12d", "Listing 22.3 GCD", t11, t12, t13, t14, t15);

        start = System.nanoTime();
        GCDEuclid.gcd(f46, f47);
        end = System.nanoTime();
        long t21 = end - start;

        start = System.nanoTime();
        GCDEuclid.gcd(f47, f48);
        end = System.nanoTime();
        long t22 = end - start;

        start = System.nanoTime();
        GCDEuclid.gcd(f48, f49);
        end = System.nanoTime();
        long t23 = end - start;

        start = System.nanoTime();
        GCDEuclid.gcd(f49, f50);
        end = System.nanoTime();
        long t24 = end - start;

        start = System.nanoTime();
        GCDEuclid.gcd(f50, f51);
        end = System.nanoTime();
        long t25 = end - start;
        
        System.out.printf("\n%-30s%-12d%-12d%-12d%-12d%-12d", "Listing 22.4 GCDEuclid", t21, t22, t23, t24, t25);
    }
}
