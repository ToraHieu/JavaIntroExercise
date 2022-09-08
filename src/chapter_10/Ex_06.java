package chapter_10;

public class Ex_06 {
    public static void main(String[] agrs) {
        StackOfIntegers primeNumbers = CreatePrimeStack();
        for (int i = 2; i < 120; i++) {
            if (isPrime(i)) {
                primeNumbers.push(i);
            }
        }
        System.out.println("The prime numbers that are less than 120 are: ");
        while (!primeNumbers.empty()) {
            System.out.println(primeNumbers.pop());
        }
    }
    
    public static StackOfIntegers CreatePrimeStack() {
        StackOfIntegers primeNumbers = new StackOfIntegers();
        
        return primeNumbers;
    }
    
    public static boolean isPrime(int number) {
        for (int i = 2; i <= number/2; i++) {
            if (number % i == 0)
                return false;
        }
        
        return true;
    }

}
