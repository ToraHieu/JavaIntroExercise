package chapter_10;
import java.math.*;

public class Ex_16 {
    public static void main(String[] agrs) {
        BigInteger bigInt =  new BigInteger("10000000000000000000000000000000000000000000000000");
        int count = 0;
        while (count < 10) {
            if (bigInt.remainder(new BigInteger("2")).equals(BigInteger.ZERO) || 
                    bigInt.remainder(new BigInteger("3")).equals(BigInteger.ZERO)) {
                System.out.println(bigInt);
                count++;
            }
            bigInt = bigInt.add(BigInteger.ONE);
        }
    }

}
