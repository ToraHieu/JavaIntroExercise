package chapter_13;

import java.math.BigInteger;

public class Ex_15 {
    /** Main method */
    public static void main(String[] args) {
      // Create and initialize two rational numbers r1 and r2.
      Rational_15 r1 = new Rational_15(4, 2);
      Rational_15 r2 = new Rational_15(2, 3);
      
      // Display results
      System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
      System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
      System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
      System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
      System.out.println(r2 + " is " + r2.doubleValue());
      
      Rational_15 t1 = new Rational_15(1, 123456789);
      Rational_15 t2 = new Rational_15(1, 123456789);
      Rational_15 t3 = new Rational_15(1, 123456789);
      System.out.println(t1.multiply(t2).multiply(t3));
    }
}

@SuppressWarnings("serial")
class Rational_15 extends Number implements Comparable<Rational_15> {
    private BigInteger numerator;
    private BigInteger denominator;
    
    public Rational_15() {
        this(new BigInteger(0 + ""), new BigInteger(1 + ""));
    }
    
    public Rational_15(long numerator, long denominator) {
        this(new BigInteger(numerator + ""), new BigInteger(denominator + ""));
    }
    
    public Rational_15(BigInteger numerator, BigInteger denominator) {
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = (denominator.compareTo(BigInteger.ZERO) > 0) ? numerator.divide(gcd) : numerator.negate().divide(gcd);
        this.denominator = denominator.abs().divide(gcd);
    }
    
    public BigInteger getNumerator() {
        return numerator;
    }
    
    public BigInteger getDenominator() {
        return denominator;
    }
    
    public Rational_15 add(Rational_15 val) {
        BigInteger n = numerator.multiply(val.getDenominator()).add(
                denominator.multiply(val.getNumerator()));
        BigInteger d = denominator.multiply(val.getDenominator());
        return new Rational_15(n, d);
    }
    
    public Rational_15 subtract(Rational_15 val) {
        BigInteger n = numerator.multiply(val.getDenominator()).subtract(
                denominator.multiply(val.getNumerator()));
        BigInteger d = denominator.multiply(val.getDenominator());      
        return new Rational_15(n, d);
    }
    
    public Rational_15 multiply(Rational_15 val) {
        BigInteger n = numerator.multiply(val.getNumerator());
        BigInteger d = denominator.multiply(val.getDenominator());
        return new Rational_15(n, d);
    }
    
    public Rational_15 divide(Rational_15 val) {
        BigInteger n = numerator.multiply(val.getDenominator());
        BigInteger d = denominator.multiply(val.getNumerator());
        return new Rational_15(n, d);
    }
    
    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE))
            return numerator + "";
          else
            return numerator + "/" + denominator;
    }
    
    @Override
    public boolean equals(Object other) {
        if ((this.subtract((Rational_15)(other))).getNumerator().equals(BigInteger.ZERO))
            return true;
          else
            return false;
    }
    
    @Override
    public int intValue() {
        return (int) doubleValue();
    }
    
    @Override 
    public float floatValue() {
        return (float) doubleValue();
    }
    
    @Override
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }
    
    @Override
    public long longValue() {
        return (long) doubleValue();
    }
    
    @Override 
    public int compareTo(Rational_15 o) {
        if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) > 0) 
            return 1;
        else if (this.subtract(o).getNumerator().compareTo(BigInteger.ZERO) < 0)
            return -1;
        else
            return 0;
    }
}
