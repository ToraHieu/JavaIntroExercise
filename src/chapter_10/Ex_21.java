package chapter_10;

import java.math.BigInteger;

public class Ex_21 {
    public static void main(String[] agrs) {
        BigInteger bigInt = new BigInteger(Long.MAX_VALUE + "");
        // bigInt is now greater than Long.MAX_VALUE
        bigInt.add(BigInteger.ONE);
        int count = 0;
        while (count < 10) {
            if (divisableByFiveOrSix(bigInt)) {
                count++;
                System.out.println(bigInt);
            }
            bigInt = bigInt.add(BigInteger.ONE);
        }
        
    }
    
    public static boolean divisableByFiveOrSix(BigInteger bigInt) {
        if (bigInt.remainder(new BigInteger("5")).equals(BigInteger.ZERO) || (bigInt.remainder(new BigInteger("6")).equals(BigInteger.ZERO)))
            return true;
        else 
            return false;
    }

}
