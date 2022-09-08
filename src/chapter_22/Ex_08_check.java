package chapter_22;

import java.io.*;

public class Ex_08_check {
    public static void main(String[] args) throws FileNotFoundException {
        DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("PrimeNumbers.dat")));

        try {
            while (true)
                System.out.println(input.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
