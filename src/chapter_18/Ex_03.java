package chapter_18;

public class Ex_03 {
    /** Displays x, where 0 ≤ x < 1 in base b with at most n digits after the decimal point. */
    public static void dec2b(double x, int b, int n) {
        // If the x is 0 then simply print 0, otherwise execute dec2b(double, int, int, String) method
        System.out.print(x == 0 ? "0" : dec2b(x, b, n, "0."));
    }

    private static String dec2b(double x, int b, int n, String result) {
        if (x == 0 || n == 0) // Base case
            return result;
        else {
            // Check on how to convert a decimal (base-10) to base-b before reviewing this code if you haven't known already.
            x *= b;
            // Separate the integral part and fractional part
            double fractionalPart = x % 1;
            double integralPart = x - fractionalPart;
            // The result takes in the current integral part and then pass the fractional part as the new x and reducing the n parameter to avoid infinity recursion.
            result = result + (int)integralPart;
            return dec2b(fractionalPart, b, n - 1, result);
        }
    }

    public static void main(String[] args) {
        dec2b(0.42, 69, 10);
    }
}
