package chapter_24;

import chapter_19.GenericStack;

public class Ex_04 {
    public static void main(String[] args) {
        final int LIMIT = 100;
        int number = 2;

        GenericStack<Integer> primeStack = new GenericStack<>();
        while (primeStack.getSize() < LIMIT) {
            if (isPrime(number))
                primeStack.push(number);
            number++;
        }

        while (primeStack.getSize() > 0) {
            System.out.print(primeStack.pop() + " ");
        }
    }

    public static boolean isPrime(int number) {
        // Assume the number is prime
        boolean isPrime = true;

        // Exercise03_21 if number is prime
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            //If true, the number is not prime
            if (number % divisor == 0) {
                // Set isPrime to false, if the number is not prime
                isPrime = false;
                break; // Exit the for loop
            }
        }

        return isPrime;
    }
}
