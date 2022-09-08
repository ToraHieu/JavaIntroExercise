package chapter_30;

import java.util.Scanner;

public class Ex_11 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter a hex number: ");
            String hex = in.next();
            if (!isHex(hex)) {
                System.out.print("Not a hex number.");
                System.exit(1);
            }
            String digits = "0123456789ABCDEF";

            System.out.print("The decimal value is: " +
                    hex.toUpperCase().chars().map(digits::indexOf).reduce(0, (e1, e2) -> (e1 * 16) + e2));
        }
    }

    private static boolean isHex(String s) {
        if ( s.length() == 0 ||
                (s.charAt(0) != '-' && Character.digit(s.charAt(0), 16) == -1))
            return false;
        if ( s.length() == 1 && s.charAt(0) == '-' )
            return false;

        for ( int i = 1 ; i < s.length() ; i++ )
            if ( Character.digit(s.charAt(i), 16) == -1 )
                return false;
        return true;
    }


}
