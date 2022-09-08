package chapter_22;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Programming Exercise 22.8 stores the prime numbers in a file named PrimeNumbers.dat.
 * Write an efficient program that reads the last 100 numbers in the file.
 * (Hint: Don’t read all numbers from the file. Skip all numbers before the last 100 numbers in the file.)
 */
public class Ex_12 {
    private static final int offset = 800; // 100 long number (8 bytes each)

    public static void main(String[] args) throws IOException {
        RandomAccessFile inout =
                new RandomAccessFile("PrimeNumbers.dat", "r");

        inout.seek(inout.length() - offset);
        StringBuilder primes = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            primes.append(inout.readLong()).append(' ');
            if (i % 10 == 0)
                primes.append('\n');
        }

        System.out.println(primes);
    }
}
