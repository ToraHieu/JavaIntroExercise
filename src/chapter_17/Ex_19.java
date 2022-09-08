package chapter_17;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Ex_19 {
    public static void main(String[] args) throws IOException {
        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter a file name: ");
            try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(scanner.next()))) {
                int byteIn;
                while ((byteIn = input.read()) != -1) {
                    System.out.print(getHex((byte)byteIn));
                }
            }
        }
    }

    @SuppressWarnings("WeakerAccess")
    public static String getHex(byte value) {
        // Convert the decimal value to binary
        String bitsValue = Ex_18.getBits(value);

        String hex = "";
        for (int i = 0; i < 2; i++) {
            String temp;
            if (i == 0)
                temp  = bitsValue.substring(0, 4);
            else
                temp = bitsValue.substring(4);
            switch (temp) {
                case "0000": temp = "0"; break;
                case "0001": temp = "1"; break;
                case "0010": temp = "2"; break;
                case "0011": temp = "3"; break;
                case "0100": temp = "4"; break;
                case "0101": temp = "5"; break;
                case "0110": temp = "6"; break;
                case "0111": temp = "7"; break;
                case "1000": temp = "8"; break;
                case "1001": temp = "9"; break;
                case "1010": temp = "A"; break;
                case "1011": temp = "B"; break;
                case "1100": temp = "C"; break;
                case "1101": temp = "D"; break;
                case "1110": temp = "E"; break;
                case "1111": temp = "F";
            }
            hex = hex.concat(temp);
        }

        return hex;
    }
}
