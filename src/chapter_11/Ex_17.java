package chapter_11;

import java.util.ArrayList;

public class Ex_17 {
    public static void main(String[] args) {
        int m = 1500;
        ArrayList<Integer> factors = findSmallestFactors(m);
        int n = 1;
        for (int i = 0; i < factors.size(); i++) {
            if (n % factors.get(i) == 0) {
                n /= factors.get(i);
            } else {
                n *= factors.get(i);
            }
        }
        System.out.println("n is " + n + " and m * n is " + m * n);
    }
    
    public static ArrayList<Integer> findSmallestFactors(int number) {
        ArrayList<Integer> factors = new ArrayList<>();
        int divisor = 2;
        while (number > 1) {
            if (number % divisor == 0) {
                number /= divisor;
                factors.add(divisor);
            } else {
                divisor++;
            }
        }
        return factors;
    }
}
