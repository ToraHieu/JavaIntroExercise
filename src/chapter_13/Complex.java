package chapter_13;

public class Complex implements Cloneable {
    private double a; // The real part
    private double b; // The imaginary part
    
    public Complex() {
        this(0, 0);
    }
    
    public Complex(double a) {
        this(a, 0);
    }
    
    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    public double getRealPart() {
        return a;
    }
    
    public double getImaginaryPart() {
        return b;
    }
    
    public Complex add(Complex c) {
        return new Complex(this.a + c.getRealPart(), this.b + c.getImaginaryPart());
    }
    
    public Complex subtract(Complex c) {
        return new Complex(this.a - c.getRealPart(), this.b - c.getImaginaryPart());
    }
    
    public Complex multiply(Complex c) {
        return new Complex(this.a * c.getRealPart() - this.b * c.getImaginaryPart(), this.b * c.getRealPart() + this.a * c.getImaginaryPart());
    }
    
    public Complex divide(Complex c) {
        return new Complex((this.a * c.getRealPart() + this.b * c.getImaginaryPart()) / (c.getRealPart() * c.getRealPart() + c.getImaginaryPart() * c.getImaginaryPart()), 
                (this.b * c.getRealPart() - this.a * c.getImaginaryPart()) / (c.getRealPart() * c.getRealPart() + c.getImaginaryPart() * c.getImaginaryPart()));

    }
    
    public double abs() {
        return Math.sqrt(a * a + b * b);
    }
    
    @Override
    public String toString() {
        if (b == 0) 
            return a + "";
        else 
            return a + " + " + b + "i";
    }
    
    @Override
    public Object clone() {
       try {
           return super.clone();
       }
       catch (CloneNotSupportedException e) {
           e.printStackTrace();
           return null;
       }
    }
}
