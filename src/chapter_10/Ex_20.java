package chapter_10;

import java.math.BigDecimal;

public class Ex_20 {
    public static void main(String[] agrs) {
        int i;
        for (i = 100; i <= 1000; i+=100) {
            BigDecimal e = approximateE(i);
            System.out.println(e.toString());
        }
    }
    
    public static BigDecimal approximateE(int precision) {
        int scale = 25;
        BigDecimal e = new BigDecimal(1);
        for (int i = 1; i < precision; i++) {
            BigDecimal divisor = BigDecimal.ONE;
            for (int j = i; j > 1; j--) {
                divisor =  divisor.multiply(new BigDecimal(j));
            }
            BigDecimal temp = BigDecimal.ONE.divide(divisor, scale, BigDecimal.ROUND_UP);
            e = e.add(temp);
        }
        
        return e;
    }

}
