package chapter_10;

import java.math.BigInteger;

public class Ex_19 {
    public static void main(String[] agrs) {
        BigInteger bigInt;
        
        for (int i = 2; i <= 100; i++) {
            bigInt = new BigInteger ((int) Math.pow(2, i) + "" );
            bigInt = bigInt.subtract(BigInteger.ONE);
            if (Ex_18.isPrime(bigInt)) 
                System.out.println("p = "  + i + " " + bigInt.toString());
        }
    }
}
