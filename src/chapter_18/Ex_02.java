package chapter_18;

public class Ex_02 {
    public static void main(String[] args) {
        System.out.print(fib(5));
    }

    public static long fib(long index) {
        if (index == 0)
            return 0;
        if (index == 1)
            return 1;
        long f0 = 0, f1 = 1, fib = f0 + f1;
        for (int i = 2; i <= index; i++) {
            fib = f0 + f1;
            f0 = f1;  // Prepare for the next iteration.
            f1 = fib; // Prepare for the next iteration.
        }

        return fib;
    }
}
