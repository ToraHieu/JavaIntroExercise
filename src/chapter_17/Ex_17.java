package chapter_17;

import java.io.FileInputStream;
import java.io.IOException;

public class Ex_17 {
    public static void main(String[] args) throws IOException {
        BitOutputStream bitOutputStream = new BitOutputStream();
        bitOutputStream.writeBit("010000100100001001101");
        bitOutputStream.close();

        try (
                // Create an input stream for the file
                FileInputStream input = new FileInputStream("Exercise17_17.dat")
        ) {
            int value;
            while ((value = input.read()) != -1) {
                System.out.print(value + " ");
            }
        }
    }
}
