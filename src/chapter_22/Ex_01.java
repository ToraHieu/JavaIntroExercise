package chapter_22;

/** (Natural numbers and their squares) Knowing that (x + 1)^2 = x^2 + 2x + 1,
 * and that multiplication is more time consuming than addition,
 * write an efficient program that displays the first ten natural numbers and their squares.
 * Knowing that (x + 1)^3 = x3 + 3x2 + 3x + 1,
 * the same process can be followed to display the cubes of these numbers.*/
public class Ex_01 {
    private static final int NUMBERS = 1000;

    public static void main(String[] args) {
//        for (int i = 0; i <= NUMBERS; i++) {
//            int j = i - 1;
//            int square = j * j + j + j + 1;
//            System.out.printf("%d^2 = %d\n", i, square);
//        }
        testSum();
        testRecursiveSum();
        testMultiplying();
        // In conclusion, for squaring, just multiply the number with itself.
    }

    private static void testRecursiveSum() {
        long start = System.nanoTime();
        for (int i = 1; i <= NUMBERS; i++) {
//            System.out.printf("%d^2 = %d\n", i, square(i, 0));
            square(i, 0);
        }
        long end = System.nanoTime();
        System.out.println("Time used in recursive sum: " + (end - start));
    }

    private static int square(int n, int result) {
        if (n == 0)
            return result;
        else
            return square(n - 1, result + n + n - 1);
    }

    private static void testSum() {
        long start = System.nanoTime();
        double square;
        for (int i = 0; i < NUMBERS; i++) {
             square = Math.pow(i, 2);
//            System.out.printf("%d^2 = %d\n", ++i, square);
        }
        long end = System.nanoTime();
        System.out.println("Time used in all sum: " + (end - start));
    }

    private static void testMultiplying() {
        long start = System.nanoTime();
        double square;
        for (int i = 0; i <= NUMBERS; i++) {
             square = i * i;
//            System.out.printf("%d^2 = %d\n", i, square);
        }
        long end = System.nanoTime();
        System.out.println("Time used with pure multiply: " + (end - start));
    }
}
