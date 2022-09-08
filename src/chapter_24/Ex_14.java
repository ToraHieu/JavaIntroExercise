package chapter_24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Ex_14 {
    public static void main(String[] args) {
        PrimeIterator iterator = new PrimeIterator(120_000L);
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}

class PrimeIterator implements Iterator<Long> {
    private Iterator<Long> iterator;

    public PrimeIterator(long limit) {
        List<Long> list = new ArrayList<>();
        list.add(2L);

        long number = 3; // A number to be tested for primeness
        long squareRoot = 2; // Check whether number <= squareRoot

        // Repeatedly find prime numbers
        while (number <= limit) {
            // Assume the number is prime
            boolean isPrime = true; // Is the current number prime?

            if (squareRoot * squareRoot < number) squareRoot++;

            // ClosestPair if number is prime
            for (int k = 0; k < list.size()
                    && list.get(k) <= squareRoot; k++) {
                if (number % list.get(k) == 0) { // If true, not prime
                    isPrime = false; // Set isPrime to false
                    break; // Exit the for loop
                }
            }

            if (isPrime) {
                list.add(number); // Add a new prime to the list
            }

            // Check if the next odd number is prime
            number += 2;
        }

        iterator = list.iterator();
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Long next() {
        return iterator.next();
    }
}
