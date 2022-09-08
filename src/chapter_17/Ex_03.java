package chapter_17;

import java.io.*;

public class Ex_03 {
    public static void main(String[] args) {
        try (DataInputStream input = new DataInputStream(new BufferedInputStream(new FileInputStream("Exercise17_03.dat")))) {
            double sum = 0;
            try {
                //noinspection InfiniteLoopStatement
                while (true) {
                    sum += input.readDouble();
                }
            } catch (EOFException ex) {
                System.out.println(sum);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
