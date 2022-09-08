package chapter_10;

import java.math.*;

public class Ex_17 {
    public static void main(String[] agrs) {
        BigInteger bigBase = new BigInteger(Math.round(Math.sqrt(Long.MAX_VALUE)) + "");
        if (bigBase.multiply(bigBase).compareTo(new BigInteger(Long.MAX_VALUE + "")) < 0)
            bigBase = bigBase.add(BigInteger.ONE);
        for (int i = 0; i < 10; i++) {
            System.out.println(bigBase.multiply(bigBase));
            bigBase = bigBase.add(BigInteger.ONE);
        }
    }

}
