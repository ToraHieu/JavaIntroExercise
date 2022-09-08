package chapter_17;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex_18 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a file: ");
        FileInputStream input = new FileInputStream(scanner.next());
        int in;
        while ((in = input.read()) != -1) {
            System.out.print(getBits(in) + " ");
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static String getBits(int value) {
        // Cut all the upper bits of the int value by casting to byte
        byte byteValue = (byte)value;
        String binary = "";
        while (byteValue != 0) {
            binary = String.valueOf(byteValue % 2).concat(binary);
            byteValue /= 2;
        }
        while (binary.length() != 8) {
            binary = "0".concat(binary);
        }
        return binary;
    }
}
