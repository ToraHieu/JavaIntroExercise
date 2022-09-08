package chapter_22;

public class Ex_04 {
    public static void main(String[] args) {
        System.out.println(exp(1, 0));
        System.out.println(exp(1, 2));
        System.out.println(exp(1, 4));
        System.out.println(exp(1, 6));
        System.out.println(exp(1, 8));
        System.out.println(exp(1, 10));

    }

    /** Assuming x and n are both positive */
    public static double exp(double x, int n) {
        /* Time complexity is O(n)
        * Using pow(x, i) and/or factorial(i) will increase time complexity to O(n^2)
        * */
        double lastFactorial = 1, lastPowerValue = 1;
        double result = 1; // First thing first: x^0/0!
        for (int i = 1; i <= n; i++) {
            result+= (lastPowerValue *= x) / (lastFactorial *= i);
        }
        return result;
    }
}
