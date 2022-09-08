package chapter_19;

import chapter_13.Complex;

public class ComplexMatrix extends ModifiedGenericMatrix<Complex> {
    @Override
    protected Complex add(Complex o1, Complex o2) {
        return o1.add(o2);
    }

    @Override
    protected Complex multiply(Complex o1, Complex o2) {
        return o1.multiply(o2);
    }

    @Override
    protected Complex zero() {
        return new Complex(); // The no parameter constructor return a Complex number with the real part and imaginary part both as 0, i.e. zero.
    }
}
