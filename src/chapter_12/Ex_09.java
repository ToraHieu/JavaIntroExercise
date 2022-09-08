package chapter_12;

import java.util.Scanner;

public class Ex_09 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a binary number: ");
        String binary = input.next();
        try {
            System.out.println("The decimal value for binary number " + binary + " is " + bin2Dec(binary));
        } 
        catch (BinaryFormatException e) {
            System.out.println(e.getMessage());
        }
        input.close();
    }

    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        int result = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(i) != '0' && binaryString.charAt(i) != '1')
                throw new BinaryFormatException("Not a binary string");
            result = result * 2 + (binaryString.charAt(i) - '0');
        }
        return result;
    }
}
