package chapter_10;

import java.math.BigInteger;

public class Ex_18 {
    public static void main(String[] agrs) {
        BigInteger bigNum = new BigInteger(Long.MAX_VALUE + "");
        int count;
        long startTime = System.currentTimeMillis();
        for (count = 0; count < 1; count++) {        
            bigNum = bigNum.add(BigInteger.ONE);
            while (!isPrime(bigNum)) {
                bigNum = bigNum.add(BigInteger.ONE);
            }
            System.out.println(bigNum.toString());
        }
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - startTime;
        System.out.println(usedTime);
        
    }
    
    public static boolean isPrime(BigInteger bigNum) {
        if (bigNum.mod(new BigInteger("2")).equals(BigInteger.ZERO))
            return false;
        BigInteger threshold = bigNum.divide(new BigInteger("2"));
        BigInteger currentNum = new BigInteger("3");
        while (currentNum.compareTo(threshold) <= 0) {
            if (bigNum.mod(currentNum).equals(BigInteger.ZERO))
                return false;
            currentNum = currentNum.add(new BigInteger("2"));
        }
        
        return true;
    }

}
