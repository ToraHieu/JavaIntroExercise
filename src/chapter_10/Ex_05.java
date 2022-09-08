package chapter_10;

import java.util.Scanner;

public class Ex_05 {
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] agrs) {
        StackOfIntegers primeFactors = new StackOfIntegers();
        
        System.out.print("Enter a possitive integer: ");
        int number = input.nextInt();
        getPrimeFactors(number, primeFactors);
        System.out.print("The prime factors are:");
        printStack(primeFactors);
        
        
    }
    
    public static void getPrimeFactors(int number, StackOfIntegers primeFactors) {
       int divider = 2;
       while (number != 1) {
           if (number % divider == 0) {
               primeFactors.push(divider);
               number /= divider;
           }
           else
               divider++;
       }
    }
    
    public static void printStack(StackOfIntegers stack) {
        while (stack.getSize() > 0) {
            System.out.print(" " + stack.pop());
        }
    }

}
