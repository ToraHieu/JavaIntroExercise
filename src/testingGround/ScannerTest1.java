package testingGround;

import java.util.Scanner;

public class ScannerTest1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s1 = input.next();
        String s2 = input.next();
        input.close();
        System.out.println(s1 + " - " + s2 );
    }
}
